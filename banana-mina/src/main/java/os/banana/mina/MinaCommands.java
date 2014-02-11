/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import os.banana.protocol.command.Command;
import os.banana.protocol.command.SimpleCommand;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public interface MinaCommands {

	String SESSION_OPEN = "__session_open__";
	String SESSION_CLOSE = "__session_close__";
	String SERVICE_ACTIVATED = "__service_activated__";
	String SERVICE_INACTIVATED = "__service_inactivated__";
	String EXCEPTION_CAUGHT = "__exception_caught__";
	String MESSAGE_RECEIVE = "__message_receive__";

	Command SESSION_OPEN_COMMAND = new SimpleCommand(SESSION_OPEN);
	Command SESSION_CLOSE_COMMAND = new SimpleCommand(SESSION_CLOSE);
	Command SERVICE_ACTIVATED_COMMAND = new SimpleCommand(SERVICE_ACTIVATED);
	Command SERVICE_INACTIVATED_COMMAND = new SimpleCommand(SERVICE_INACTIVATED);
}
