/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.hbgb;

import org.springframework.stereotype.Component;

import os.banana.protocol.Server;
import os.banana.protocol.command.AbstractRequestCommandController;
import os.banana.protocol.command.RequestId;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@Component
@RequestId("2011")
public class RealDataCommandController extends
		AbstractRequestCommandController<HBGBCommand> {

	@Override
	protected HBGBCommand doRequest(HBGBCommand command, Server server) {
		command.setId("2012");
		return command;
	}

}
