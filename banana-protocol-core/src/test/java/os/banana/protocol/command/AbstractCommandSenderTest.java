/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol.command;

import static org.junit.Assert.*;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import os.banana.protocol.FutureManager;
import os.banana.protocol.SFuture;
import static org.easymock.EasyMock.*;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@RunWith(EasyMockRunner.class)
public class AbstractCommandSenderTest {

	@TestSubject
	private AbstractCommandSender commandSender = new TestCommandSender();
	private FutureManager futureManager = new FutureManager();
	@Mock
	private Command request;
	@Mock
	private Command response1;
	@Mock
	private Command response2;

	@Test
	public void testSend() {
		expect(request.buildSendSerialNumber()).andReturn("sn100");
		expect(response1.getSendedSerialNumber()).andReturn("sn100");
		expect(response2.getSendedSerialNumber()).andReturn("sn101").times(2);

		replay(request, response1, response2);
		commandSender.setFutureManager(futureManager);
		SFuture<Command> future = commandSender.send(request);

		assertNull(futureManager.getFuture(response2));
		assertEquals(future, futureManager.getFuture(response1));
		assertNull(futureManager.getFuture(response2));
		verify(request, response1, response2);
	}

	class TestCommandSender extends AbstractCommandSender {

		@Override
		protected void doSend(Command command) {
			assertEquals(request, command);
		}

	}

}
