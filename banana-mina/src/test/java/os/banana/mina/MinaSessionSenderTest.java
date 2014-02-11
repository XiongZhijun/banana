/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import static org.junit.Assert.*;

import org.apache.mina.api.IoSession;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

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

	@Test
	public void testSetSession() {
		assertEquals(session, sender.getSession());
	}

	@Test
	public void testCreateWithSession() {
		MinaSessionSender sender = new MinaSessionSender(session);
		assertEquals(session, sender.getSession());
	}

}
