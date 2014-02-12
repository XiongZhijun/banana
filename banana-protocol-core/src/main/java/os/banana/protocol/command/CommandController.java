/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol.command;

import os.banana.protocol.Server;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public interface CommandController<T extends Command> {

	CommandController<?> EMPTY_COMMAND_CONTROLLER = new EmptyCommandController();

	void handle(T command, CommandSender<T> sender, Server server);

	class EmptyCommandController implements CommandController<Command> {

		public void handle(Command command, CommandSender<Command> sender,
				Server server) {
		}

	}
}
