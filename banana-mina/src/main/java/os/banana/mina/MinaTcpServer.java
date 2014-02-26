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

import os.banana.protocol.AbstractServer;
import os.banana.protocol.Server;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class MinaTcpServer extends AbstractServer implements Server,
		InitializingBean {

	/** 端口号 */
	private int port;
	private NioTcpServer tcpServer;
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
			selectorLoopPool = new FixedSelectorLoopPool("Server-" + getCode(),
					Runtime.getRuntime().availableProcessors() + 1);
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

}
