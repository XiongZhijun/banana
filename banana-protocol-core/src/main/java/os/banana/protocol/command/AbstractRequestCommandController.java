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
public abstract class AbstractRequestCommandController<T extends Command>
		implements CommandController<T> {

	public void handle(T command, CommandSender sender, Server server) {
		if (command == null) {
			return;
		}
		T response = doRequest(command, server);
		sender.send(response);
	}

	protected abstract T doRequest(T command, Server server);

}
