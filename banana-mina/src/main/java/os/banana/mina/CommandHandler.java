/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import org.apache.mina.api.AbstractIoHandler;
import org.apache.mina.api.IoService;
import org.apache.mina.api.IoSession;
import org.springframework.beans.factory.annotation.Autowired;

import os.banana.mina.command.Command;
import os.banana.mina.command.CommandDispatcher;
import os.banana.mina.command.SimpleCommand;
import static os.banana.mina.MinaCommands.*;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class CommandHandler extends AbstractIoHandler {
	@Autowired
	private CommandDispatcher commandDispatcher;
	@Autowired
	private Server server;

	@Override
	public void messageReceived(IoSession session, Object message) {
		MinaSessionSender sender = new MinaSessionSender(session);
		if (message instanceof Command) {
			commandDispatcher.doDispatch((Command) message, sender, server);
		} else {
			commandDispatcher.doDispatch(new SimpleCommand(MESSAGE_RECEIVE,
					message), sender, server);
		}
	}

	@Override
	public void sessionOpened(IoSession session) {
		commandDispatcher.doDispatch(SESSION_OPEN_COMMAND,
				new MinaSessionSender(session), server);
	}

	@Override
	public void sessionClosed(IoSession session) {
		commandDispatcher.doDispatch(SESSION_CLOSE_COMMAND,
				new MinaSessionSender(session), server);
	}

	@Override
	public void serviceActivated(IoService service) {
		commandDispatcher.doDispatch(SERVICE_ACTIVATED_COMMAND, null, server);
	}

	@Override
	public void serviceInactivated(IoService service) {
		commandDispatcher.doDispatch(SERVICE_INACTIVATED_COMMAND, null, server);
	}

	@Override
	public void exceptionCaught(IoSession session, Exception cause) {
		commandDispatcher.doDispatch(
				new SimpleCommand(EXCEPTION_CAUGHT, cause),
				new MinaSessionSender(session), server);
	}

	public CommandDispatcher getCommandDispatcher() {
		return commandDispatcher;
	}

	public void setCommandDispatcher(CommandDispatcher commandDispatcher) {
		this.commandDispatcher = commandDispatcher;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

}
