/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import os.banana.mina.MinaCommands;
import os.banana.protocol.Server;
import os.banana.protocol.command.CommandController;
import os.banana.protocol.command.CommandSender;
import os.banana.protocol.command.RequestId;
import os.banana.protocol.command.SimpleCommand;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@RequestId(MinaCommands.EXCEPTION_CAUGHT)
public class ExceptionCommandController implements
		CommandController<SimpleCommand> {
	private static final Log log = LogFactory
			.getLog(ExceptionCommandController.class);

	public void handle(SimpleCommand command, CommandSender sender,
			Server server) {
		Exception e = (Exception) command.getContent();
		log.warn(
				"Command from " + server.getName() + " : "
						+ command.getTerminalCode()
						+ " throw exception. Command is : "
						+ command.toString(), e);
	}

}
