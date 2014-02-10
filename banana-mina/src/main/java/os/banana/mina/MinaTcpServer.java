/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import java.net.InetSocketAddress;

import org.apache.mina.api.IoFilter;
import org.apache.mina.api.IoHandler;
import org.apache.mina.transport.nio.NioTcpServer;
import org.apache.mina.transport.tcp.DefaultTcpSessionConfig;
import org.apache.mina.transport.tcp.TcpSessionConfig;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class MinaTcpServer implements Server {

	/** 端口号 */
	private int port;
	private NioTcpServer tcpServer;
	private IoHandler handler;
	private IoFilter[] filters = new IoFilter[0];
	private boolean reuseAddress = false;
	private TcpSessionConfig sessionConfig = new DefaultTcpSessionConfig();

	public void start() {
		tcpServer = new NioTcpServer();
		tcpServer.setFilters(filters);
		tcpServer.setReuseAddress(reuseAddress);
		tcpServer.setSessionConfig(sessionConfig);
		tcpServer.setIoHandler(handler);
		tcpServer.bind(new InetSocketAddress(port));
	}

	public void stop() {
		tcpServer.unbind();
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

	public void setFilters(IoFilter... filters) {
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

}
