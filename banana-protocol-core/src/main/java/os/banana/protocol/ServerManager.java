/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class ServerManager extends ReadWriteLockSupport {
	private Map<String, Server> serverMap = new HashMap<String, Server>();

	public void regist(Server server) {
		String code = server.getCode();
		writeLock.lock();
		try {
			Server existServer = getServer(code);
			if (existServer == server) {
				return;
			}
			if (existServer != null) {
				unregist(code);
			}
			serverMap.put(code, server);
		} finally {
			writeLock.unlock();
		}
	}

	public Server getServer(String code) {
		readLock.lock();
		try {
			return serverMap.get(code);
		} finally {
			readLock.unlock();
		}
	}

	public void unregist(String code) {
		writeLock.lock();
		try {
			Server server = serverMap.get(code);
			if (server != null) {
				server.stop();
			}
			serverMap.remove(code);
		} finally {
			writeLock.unlock();
		}
	}

	public Collection<Server> getAllServers() {
		List<Server> servers = new ArrayList<Server>();
		servers.addAll(serverMap.values());
		return servers;
	}

}
