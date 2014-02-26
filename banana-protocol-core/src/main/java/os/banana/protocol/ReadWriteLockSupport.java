/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public abstract class ReadWriteLockSupport {
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	protected Lock readLock = readWriteLock.readLock();
	protected Lock writeLock = readWriteLock.writeLock();

	public Lock getReadLock() {
		return readLock;
	}

	public Lock getWriteLock() {
		return writeLock;
	}
}
