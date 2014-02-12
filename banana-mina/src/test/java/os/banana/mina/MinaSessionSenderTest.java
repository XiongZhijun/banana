/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import static org.junit.Assert.assertEquals;
import static org.easymock.EasyMock.*;

import org.apache.mina.api.IoSession;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import os.banana.protocol.FutureManager;
import os.banana.protocol.command.Command;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@RunWith(EasyMockRunner.class)
public class MinaSessionSenderTest {

	@TestSubject
	private MinaSessionSender sender = new MinaSessionSender();
	@Mock
	private IoSession session;
	@Mock
	private Command command;

	@Test
	public void testSetSession() {
		replay();
		assertEquals(session, sender.getSession());
		verify();
	}

	@Test
	public void testCreateWithSession() {
		MinaSessionSender sender = new MinaSessionSender(session);
		assertEquals(session, sender.getSession());
	}

	@Test
	public void testSend() {
		session.write(command);
		replay(session);
		sender.setFutureManager(new FutureManager());
		sender.send(command);
		verify(session);
	}

}
