/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public abstract class AbstractServer extends ReadWriteLockSupport implements
		Server {
	private Map<String, TerminalSession> terminals = new HashMap<String, TerminalSession>();

	/** 服务名称 */
	private String name;
	/** 服务编码，这个必须要设置的 */
	private String code;
	@Autowired
	private ServerManager serverManager;

	public void registTerminal(TerminalSession terminalSession) {
		writeLock.lock();
		try {
			terminals.put(terminalSession.getCode(), terminalSession);
		} finally {
			writeLock.unlock();
		}
	}

	public void unregistTerminal(String code) {
		writeLock.lock();
		try {
			terminals.remove(code);
		} finally {
			writeLock.unlock();
		}
	}

	public TerminalSession getTerminalSession(String code) {
		readLock.lock();
		try {
			return terminals.get(code);
		} finally {
			readLock.unlock();
		}
	}

	public Set<String> getAllTerminals() {
		readLock.lock();
		try {
			return terminals.keySet();
		} finally {
			readLock.unlock();
		}
	}

	public boolean isStopped() {
		return !isRunning();
	}

	public void setServerManager(ServerManager serverManager) {
		this.serverManager = serverManager;
	}

	public void afterPropertiesSet() throws Exception {
		serverManager.regist(this);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
