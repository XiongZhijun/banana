/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.mina.api.AbstractIoHandler;
import org.apache.mina.api.IoFuture;
import org.apache.mina.api.IoSession;
import org.apache.mina.transport.nio.NioTcpClient;

import os.banana.mina.utils.ByteBufferUtils;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class MinaClient implements Runnable {

	private int port;
	private String message;
	private CountDownLatch countDownLatch;

	public MinaClient(int port, String message, CountDownLatch countDownLatch) {
		super();
		this.port = port;
		this.message = message;
		this.countDownLatch = countDownLatch;
	}

	public void run() {
		NioTcpClient client = new NioTcpClient();
		client.setFilters();
		client.setIoHandler(new ClientIoHandler());
		try {
			IoFuture<IoSession> future = client.connect(new InetSocketAddress(
					"localhost", port));
			future.get();
			assertTrue(countDownLatch.await(1, TimeUnit.SECONDS));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	class ClientIoHandler extends AbstractIoHandler {
		@Override
		public void sessionOpened(IoSession session) {
			super.sessionOpened(session);
			session.write(ByteBufferUtils.toByteBuffer(message));
			countDownLatch.countDown();
		}
	}

}
