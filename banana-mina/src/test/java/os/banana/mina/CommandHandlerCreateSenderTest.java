/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import static org.easymock.EasyMock.createControl;
import static org.junit.Assert.assertEquals;

import org.apache.mina.api.IoSession;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import os.banana.protocol.AbstractServer;
import os.banana.protocol.FutureManager;
import os.banana.protocol.Server;
import os.banana.protocol.command.Command;
import os.banana.protocol.command.CommandDispatcher;
import os.banana.protocol.command.CommandSender;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@ContextConfiguration
public class CommandHandlerCreateSenderTest extends
		AbstractJUnit4SpringContextTests {
	@Autowired
	private CommandHandler commandHandler;
	@Autowired
	private FutureManager futureManager;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateCommandSender() {
		IMocksControl control = createControl();
		IoSession session = control.createMock(IoSession.class);
		control.replay();
		MinaSessionSender commandSender = (MinaSessionSender) commandHandler
				.createCommandSender(session);
		assertEquals(futureManager, commandSender.getFutureManager());
		control.verify();
	}

	public static class TestCommandDispatcher implements CommandDispatcher {
		public void doDispatch(Command command, CommandSender sender,
				Server server) {
		}
	}

	public static class TestServer extends AbstractServer {

		public void start() {

		}

		public void stop() {

		}

		public boolean isRunning() {
			return false;
		}

	}
}
