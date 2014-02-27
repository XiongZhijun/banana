/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import static os.banana.mina.MinaCommands.EXCEPTION_CAUGHT;
import static os.banana.mina.MinaCommands.MESSAGE_RECEIVE;
import static os.banana.mina.MinaCommands.SERVICE_ACTIVATED_COMMAND;
import static os.banana.mina.MinaCommands.SERVICE_INACTIVATED_COMMAND;
import static os.banana.mina.MinaCommands.SESSION_CLOSE_COMMAND;
import static os.banana.mina.MinaCommands.SESSION_OPEN_COMMAND;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.api.AbstractIoHandler;
import org.apache.mina.api.IoService;
import org.apache.mina.api.IoSession;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import os.banana.protocol.Server;
import os.banana.protocol.command.Command;
import os.banana.protocol.command.CommandDispatcher;
import os.banana.protocol.command.CommandSender;
import os.banana.protocol.command.SimpleCommand;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class CommandHandler extends AbstractIoHandler implements
		ApplicationContextAware {
	private static final Log log = LogFactory.getLog(CommandHandler.class);
	@Autowired
	private CommandDispatcher commandDispatcher;
	@Autowired
	private Server server;
	private ApplicationContext applicationContext;

	@Override
	public void messageReceived(IoSession session, Object message) {
		CommandSender sender = createCommandSender(session);
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
				createCommandSender(session), server);
	}

	@Override
	public void sessionClosed(IoSession session) {
		commandDispatcher.doDispatch(SESSION_CLOSE_COMMAND,
				createCommandSender(session), server);
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
		try {
			commandDispatcher.doDispatch(new SimpleCommand(EXCEPTION_CAUGHT,
					cause), createCommandSender(session), server);
		} catch (Exception e) {
			log.error("dispatch exception command failed.", e);
		}
	}

	protected CommandSender createCommandSender(IoSession session) {
		MinaSessionSender sender = new MinaSessionSender(session);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(sender);
		return sender;
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

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
