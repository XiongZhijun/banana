/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol.command;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import os.banana.protocol.Server;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@RunWith(EasyMockRunner.class)
public class SimpleCommandDispatcherTest {
	@TestSubject
	private SimpleCommandDispatcher dispatcher = new SimpleCommandDispatcher();
	@Mock
	private Command command;
	@Mock
	private CommandSender sender;
	@Mock
	private Server server;
	@Mock
	private CommandControllerMapping controllerMapping;
	@Mock
	private CommandController<Command> controller;

	@Test
	public void testDoDispatch() {
		expect(controllerMapping.findCommandController(command)).andReturn(
				controller);
		controller.handle(command, sender, server);
		replay(controllerMapping, controller);
		dispatcher.doDispatch(command, sender, server);
		verify(controllerMapping, controller);
	}

}
