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
	private NioTcpServer nioTcpServer;
	private IoHandler ioHandler;

	public void start() {
		nioTcpServer = new NioTcpServer();
		nioTcpServer.setIoHandler(ioHandler);
		nioTcpServer.bind(new InetSocketAddress(port));
	}

	public void stop() {
		nioTcpServer.unbind();
	}

	/**
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return
	 */
	public int getPort() {
		return this.port;
	}

	/**
	 * @param abstractIoHandler
	 */
	public void setIoHandler(IoHandler ioHandler) {
		this.ioHandler = ioHandler;
	}

}
