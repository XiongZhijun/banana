/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import java.net.InetSocketAddress;

import org.apache.mina.api.IoFilter;
import org.apache.mina.api.IoHandler;
import org.apache.mina.service.executor.IoHandlerExecutor;
import org.apache.mina.transport.nio.FixedSelectorLoopPool;
import org.apache.mina.transport.nio.NioTcpServer;
import org.apache.mina.transport.nio.SelectorLoopPool;
import org.apache.mina.transport.tcp.DefaultTcpSessionConfig;
import org.apache.mina.transport.tcp.TcpSessionConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import os.banana.protocol.ReadWriteLockSupport;
import os.banana.protocol.Server;
import os.banana.protocol.ServerManager;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class MinaTcpServer extends ReadWriteLockSupport implements Server,
		InitializingBean {

	/** 端口号 */
	private int port;
	/** 服务名称 */
	private String name;
	/** 服务编码，这个必须要设置的 */
	private String code;
	private NioTcpServer tcpServer;
	@Autowired
	private ServerManager serverManager;
	@Autowired
	private IoHandler handler;
	@Autowired(required = false)
	private IoFilter[] filters = new IoFilter[0];
	private boolean reuseAddress = false;
	@Autowired(required = false)
	private TcpSessionConfig sessionConfig = new DefaultTcpSessionConfig();
	private SelectorLoopPool selectorLoopPool;
	private IoHandlerExecutor handlerExecutor;
	private boolean running = false;

	public void start() {
		if (isRunning()) {
			return;
		}
		if (selectorLoopPool == null) {
			selectorLoopPool = new FixedSelectorLoopPool("Server", Runtime
					.getRuntime().availableProcessors() + 1);
		}
		tcpServer = new NioTcpServer(sessionConfig, selectorLoopPool,
				handlerExecutor);
		tcpServer.setFilters(filters);
		tcpServer.setReuseAddress(reuseAddress);
		tcpServer.setSessionConfig(sessionConfig);
		tcpServer.setIoHandler(handler);
		tcpServer.bind(new InetSocketAddress(port));
		running = true;
	}

	public void stop() {
		if (tcpServer != null) {
			tcpServer.unbind();
		}
	}

	public boolean isRunning() {
		return running;
	}

	public boolean isStopped() {
		return !isRunning();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getPort() {
		return this.port;
	}

	public void setHandler(IoHandler handler) {
		this.handler = handler;
	}

	@Autowired
	public void setFilters(IoFilter[] filters) {
		if (filters == null) {
			return;
		}
		this.filters = filters;
	}

	public void setReuseAddress(boolean reuseAddress) {
		this.reuseAddress = reuseAddress;
	}

	public void setSessionConfig(TcpSessionConfig sessionConfig) {
		if (sessionConfig == null) {
			return;
		}
		this.sessionConfig = sessionConfig;
	}

	public void setSelectorLoopPool(SelectorLoopPool selectorLoopPool) {
		this.selectorLoopPool = selectorLoopPool;
	}

	public void setHandlerExecutor(IoHandlerExecutor handlerExecutor) {
		this.handlerExecutor = handlerExecutor;
	}

	public void setServerManager(ServerManager serverManager) {
		this.serverManager = serverManager;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void afterPropertiesSet() throws Exception {
		serverManager.regist(this);
	}

}
