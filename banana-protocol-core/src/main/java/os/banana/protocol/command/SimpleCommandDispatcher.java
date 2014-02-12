/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol.command;

import org.springframework.beans.factory.annotation.Autowired;

import os.banana.protocol.Server;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class SimpleCommandDispatcher implements CommandDispatcher {
	@Autowired
	private CommandControllerMapping controllerMapping;

	public void doDispatch(Command command, CommandSender sender, Server server) {
		CommandController<Command> controller = controllerMapping
				.findCommandController(command);
		controller.handle(command, sender, server);
	}

	public CommandControllerMapping getControllerMapping() {
		return controllerMapping;
	}

	public void setControllerMapping(CommandControllerMapping controllerMapping) {
		this.controllerMapping = controllerMapping;
	}

}
