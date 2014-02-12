/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.mina.api.AbstractIoHandler;
import org.apache.mina.api.IoSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@ContextConfiguration
public class MinaTcpServerTest extends AbstractJUnit4SpringContextTests {

	private static final String HELLO = "hello";
	@Autowired
	private MinaTcpServer server;
	private static CountDownLatch countDownLatch;

	@Test
	public void testSetPort() {
		assertEquals(29999, server.getPort());
	}

	@Test
	public void testGetName() {
		assertEquals("MinaServer1", server.getName());
	}

	@Test
	public void testStart() throws InterruptedException {
		countDownLatch = new CountDownLatch(2);
		new Thread(new MinaClient(server.getPort(), HELLO, countDownLatch))
				.start();
		assertTrue(countDownLatch.await(1, TimeUnit.SECONDS));
	}

	public static class TestIoHandler extends AbstractIoHandler {
		@Override
		public void messageReceived(IoSession session, Object message) {
			assertEquals(HELLO, message);
			countDownLatch.countDown();
		}
	}
}
