/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.mina.api.AbstractIoHandler;
import org.apache.mina.api.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class MinaTcpServerTest {

	private MinaTcpServer server;

	@Before
	public void setUp() {
		server = new MinaTcpServer();
	}

	@Test
	public void testSetPort() {
		server.setPort(100);
		assertEquals(100, server.getPort());
		server.setPort(999);
		assertEquals(999, server.getPort());

	}

	@Test
	public void testStart() throws InterruptedException {
		int port = 19999;
		final String hello = "hello";
		final CountDownLatch countDownLatch = new CountDownLatch(2);
		server.setPort(port);
		server.setFilters(new ProtocolCodecFilter<String, ByteBuffer, Void, Void>(
				new StringEncoder(), new StringDecoder()));
		server.setSessionConfig(null);
		server.setHandler(new AbstractIoHandler() {
			@Override
			public void messageReceived(IoSession session, Object message) {
				super.messageReceived(session, message);
				assertTrue(message instanceof String);
				assertEquals(hello, message);
				countDownLatch.countDown();
			}
		});
		server.start();
		new Thread(new MinaClient(port, hello, countDownLatch)).start();
		assertTrue(countDownLatch.await(1, TimeUnit.SECONDS));
		server.stop();
	}
}
