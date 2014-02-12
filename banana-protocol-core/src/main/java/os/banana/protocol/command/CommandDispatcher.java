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
public interface CommandDispatcher {

	void doDispatch(Command command, CommandSender<Command> sender,
			Server server);
}
