/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import os.banana.protocol.command.Command;
import os.banana.protocol.command.CommandControllerMapping;
import os.banana.protocol.command.CommandDispatcher;
import os.banana.protocol.command.CommandSender;
import os.banana.protocol.command.SimpleCommandDispatcher;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@ContextConfiguration
public class DefaultStrategyManagerTest extends
		AbstractJUnit4SpringContextTests {

	private DefaultStrategyManager parent;
	@Autowired
	private CommandControllerMapping controllerMapping;
	private DefaultStrategyManager child;

	@Before
	public void setUp() {
		parent = new DefaultStrategyManager(applicationContext,
				"classpath:os/banana/protocol/DefaultStrategyManagerTest1.properties");
		child = new DefaultStrategyManager(
				applicationContext,
				"classpath:os/banana/protocol/DefaultStrategyManagerTest2.properties",
				parent);
	}

	@Test
	public void testGetDefaultStrategy() {
		SimpleCommandDispatcher dispatcher = (SimpleCommandDispatcher) parent
				.getDefaultStrategy(CommandDispatcher.class);
		assertNotNull(dispatcher);
		assertEquals(controllerMapping, dispatcher.getControllerMapping());
	}

	@Test
	public void testGetDefaultStrategies() {
		List<FutureManager> futureManagers = child
				.getDefaultStrategies(FutureManager.class);
		assertEquals(2, futureManagers.size());
		assertNotEquals(futureManagers.get(0), futureManagers.get(1));
	}

	@Test
	public void testWithParent() {
		CommandDispatcher dispatcher = child
				.getDefaultStrategy(CommandDispatcher.class);
		assertNotNull(dispatcher);
		assertTrue(dispatcher instanceof TestCommandDispatcher);
	}

	public static class TestCommandDispatcher implements CommandDispatcher {

		public void doDispatch(Command command, CommandSender sender,
				Server server) {

		}

	}
}
