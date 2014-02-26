/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class TerminalManager {
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private Lock readLock = readWriteLock.readLock();
	private Lock writeLock = readWriteLock.writeLock();

	private Map<String, TerminalSession> terminalMap = new HashMap<String, TerminalSession>();

	public void regist(TerminalSession terminalSession) {
		writeLock.lock();
		try {
			terminalMap.put(terminalSession.getCode(), terminalSession);
		} finally {
			writeLock.unlock();
		}
	}

	public TerminalSession get(String code) {
		readLock.lock();
		try {
			return terminalMap.get(code);
		} finally {
			readLock.unlock();
		}
	}

	public void unregist(String code) {
		writeLock.lock();
		try {
			terminalMap.remove(code);
		} finally {
			writeLock.unlock();
		}
	}

}
