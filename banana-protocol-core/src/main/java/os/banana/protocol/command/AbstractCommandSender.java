/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol.command;

import os.banana.protocol.SFuture;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class AbstractCommandSender implements CommandSender {

	public <T extends Command> SFuture<T> send(T command) {
		// TODO Auto-generated method stub
		return null;
	}

}
