/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import java.net.InetSocketAddress;

import org.apache.mina.api.IoHandler;
import org.apache.mina.transport.nio.NioTcpServer;

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

	public void start() {
		tcpServer = new NioTcpServer();
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

}
