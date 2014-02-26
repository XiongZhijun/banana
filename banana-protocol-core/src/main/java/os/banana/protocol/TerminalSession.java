/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol;

import os.banana.protocol.command.CommandSender;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public interface TerminalSession {

	CommandSender getCommandSender();

	Server getServer();

	String getCode();
}
