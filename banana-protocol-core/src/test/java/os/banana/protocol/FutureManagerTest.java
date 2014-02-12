/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import os.banana.protocol.command.Command;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@RunWith(EasyMockRunner.class)
public class FutureManagerTest {
	@TestSubject
	private FutureManager manager = new FutureManager();
	@Mock
	private Command command;
	@Mock
	private SFuture<Command> future;

	@Test
	public void testRegistFuture() {
		expect(command.buildSendSerialNumber()).andReturn("sn1");
		expect(command.getSendedSerialNumber()).andReturn("sn1");
		replay(command, future);
		manager.registFuture(command, future);
		assertEquals(future, manager.getFuture(command));
		verify(command, future);
	}

	@Test
	public void testGetFutureFailed() {
		expect(command.buildSendSerialNumber()).andReturn("sn2");
		expect(command.getSendedSerialNumber()).andReturn("sn3");
		replay(command, future);
		manager.registFuture(command, future);
		assertNull(manager.getFuture(command));
		verify(command, future);
	}

}
