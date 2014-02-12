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
public abstract class AbstractCommandSender<T extends Command> implements
		CommandSender<T> {

	public SFuture<T> send(T command) {
		doSend(command);
		return null;
	}

	protected abstract void doSend(T command);

}
