/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class TerminalManager extends ReadWriteLockSupport {

	private Map<String, TerminalSession> terminalMap = new HashMap<String, TerminalSession>();

	public void regist(TerminalSession terminalSession) {
		getWriteLock().lock();
		try {
			terminalMap.put(terminalSession.getCode(), terminalSession);
		} finally {
			getWriteLock().unlock();
		}
	}

	public TerminalSession get(String code) {
		getReadLock().lock();
		try {
			return terminalMap.get(code);
		} finally {
			getReadLock().unlock();
		}
	}

	public void unregist(String code) {
		getWriteLock().lock();
		try {
			terminalMap.remove(code);
		} finally {
			getWriteLock().unlock();
		}
	}

}
