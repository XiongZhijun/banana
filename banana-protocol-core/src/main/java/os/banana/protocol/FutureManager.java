/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import os.banana.protocol.command.Command;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class FutureManager {

	private static final int FIVE_MINUTES = 5;
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private Lock readLock = readWriteLock.readLock();
	private Lock writeLock = readWriteLock.writeLock();
	private Map<Object, SFuture<?>> futureMap = new HashMap<Object, SFuture<?>>();
	private long period = FIVE_MINUTES;
	private TimeUnit timeUnit = TimeUnit.MINUTES;

	public FutureManager() {
		super();
		new Timer("FutureManager").schedule(new TimeoutFutureCleaner(),
				timeUnit.toMillis(period), timeUnit.toMillis(period));
	}

	@SuppressWarnings("unchecked")
	public <T extends Command> SFuture<T> getFuture(T command) {
		readLock.lock();
		try {
			return (SFuture<T>) futureMap.get(command.getSendedSerialNumber());
		} finally {
			readLock.unlock();
		}
	}

	public <T extends Command> SFuture<T> registFuture(T command,
			SFuture<T> future) {
		writeLock.lock();
		try {
			futureMap.put(command.buildSendSerialNumber(), future);
			return future;
		} finally {
			writeLock.unlock();
		}
	}

	public void setPeriod(long period) {
		this.period = period;
	}

	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}

	class TimeoutFutureCleaner extends TimerTask {
		@Override
		public void run() {
			writeLock.lock();
			try {
				removeTimeoutFuture();
			} finally {
				writeLock.unlock();
			}
		}

		private void removeTimeoutFuture() {
			Object[] array = futureMap.keySet().toArray();
			for (Object key : array) {
				SFuture<?> future = futureMap.get(key);
				if (future != null && future.isTimeout()) {
					futureMap.remove(key);
				}
			}
		}
	}

}
