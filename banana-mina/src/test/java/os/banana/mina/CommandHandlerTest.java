/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.isNull;
import static org.easymock.EasyMock.notNull;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static os.banana.mina.MinaCommands.SERVICE_ACTIVATED_COMMAND;
import static os.banana.mina.MinaCommands.SERVICE_INACTIVATED_COMMAND;
import static os.banana.mina.MinaCommands.SESSION_CLOSE_COMMAND;
import static os.banana.mina.MinaCommands.SESSION_OPEN_COMMAND;

import org.apache.mina.api.IoService;
import org.apache.mina.api.IoSession;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import os.banana.mina.command.Command;
import os.banana.mina.command.CommandDispatcher;
import os.banana.mina.command.SimpleCommand;


/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@RunWith(EasyMockRunner.class)
public class CommandHandlerTest {
	@TestSubject
	private CommandHandler handler = new CommandHandler();
	@Mock
	private CommandDispatcher dispatcher;
	@Mock
	private Server server;
	@Mock
	private IoSession session;
	@Mock
	private IoService service;
	@Mock
	private Command command;

	@Test
	public void testMessageReceivedIoSessionObject() {
		dispatcher.doDispatch(eq(command), notNull(MinaSessionSender.class),
				eq(server));
		dispatcher.doDispatch(anyObject(SimpleCommand.class),
				notNull(MinaSessionSender.class), eq(server));
		replay(dispatcher);
		handler.messageReceived(session, command);
		handler.messageReceived(session, new Object());
		verify(dispatcher);
	}

	@Test
	public void testSessionOpenedIoSession() {
		dispatcher.doDispatch(eq(SESSION_OPEN_COMMAND),
				anyObject(MinaSessionSender.class), eq(server));
		replay(dispatcher);
		handler.sessionOpened(session);
		verify(dispatcher);
	}

	@Test
	public void testSessionClosedIoSession() {
		dispatcher.doDispatch(eq(SESSION_CLOSE_COMMAND),
				anyObject(MinaSessionSender.class), eq(server));
		replay(dispatcher);
		handler.sessionClosed(session);
		verify(dispatcher);
	}

	@Test
	public void testServiceActivatedIoService() {
		dispatcher.doDispatch(eq(SERVICE_ACTIVATED_COMMAND),
				isNull(MinaSessionSender.class), eq(server));
		replay(dispatcher);
		handler.serviceActivated(service);
		verify(dispatcher);
	}

	@Test
	public void testServiceInactivatedIoService() {
		dispatcher.doDispatch(eq(SERVICE_INACTIVATED_COMMAND),
				isNull(MinaSessionSender.class), eq(server));
		replay(dispatcher);
		handler.serviceInactivated(service);
		verify(dispatcher);
	}

	@Test
	public void testExceptionCaughtIoSessionException() {
		dispatcher.doDispatch(anyObject(SimpleCommand.class),
				notNull(MinaSessionSender.class), eq(server));
		replay(dispatcher);
		Exception cause = new Exception();
		handler.exceptionCaught(session, cause);
		verify(dispatcher);
	}

}
