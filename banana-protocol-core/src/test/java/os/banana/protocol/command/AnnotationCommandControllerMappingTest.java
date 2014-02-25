/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol.command;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import os.banana.protocol.Matcher;
import os.banana.protocol.command.CommandController.EmptyCommandController;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
// @RunWith(EasyMockRunner.class)
@ContextConfiguration
public class AnnotationCommandControllerMappingTest extends
		AbstractJUnit4SpringContextTests {
	@Autowired
	private AnnotationCommandControllerMapping mapping;
	@Autowired
	@Qualifier("cc1")
	private CommandController<?> commandController1;
	@Autowired
	@Qualifier("cc2")
	private CommandController<?> commandController2;
	@Autowired
	@Qualifier("cc3")
	private CommandController<?> commandController3;

	private Command command1;
	private Command command2;
	private Command command3_1;
	private Command command3_2;
	private Command command4;

	@Before
	public void setUp() {
		command1 = new SimpleCommand("command1");
		command2 = new SimpleCommand("command2");
		command3_1 = new SimpleCommand("command3_1");
		command3_2 = new SimpleCommand("command3_2");
		command4 = new SimpleCommand("command4");
	}

	@Test
	public void testFindCommandController() {
		assertEquals(commandController1,
				mapping.findCommandController(command1));
		assertEquals(commandController2,
				mapping.findCommandController(command2));
		assertEquals(commandController3,
				mapping.findCommandController(command3_1));
		assertEquals(commandController3,
				mapping.findCommandController(command3_2));
		assertEquals(CommandController.EMPTY_COMMAND_CONTROLLER,
				mapping.findCommandController(command4));
	}

	@RequestId("command1")
	public static class CommandController1 extends EmptyCommandController {
	}

	@RequestId("command2")
	public static class CommandController2 extends EmptyCommandController {
	}

	public static class CommandController3 extends EmptyCommandController
			implements Matcher<Command> {

		public boolean match(Command target) {
			return target.getId().startsWith("command3");
		}

	}

}
