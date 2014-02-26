/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol;

import java.util.concurrent.locks.Lock;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public interface Server {

	String getName();

	String getCode();

	void start();

	void stop();
	
	boolean isRunning();
	
	boolean isStopped();
	
	Lock getReadLock();
	
	Lock getWriteLock();
}
