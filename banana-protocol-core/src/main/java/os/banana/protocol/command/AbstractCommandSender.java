/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol.command;

import org.springframework.beans.factory.annotation.Autowired;

import os.banana.protocol.DefaultFuture;
import os.banana.protocol.FutureManager;
import os.banana.protocol.SFuture;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public abstract class AbstractCommandSender<T extends Command> implements
		CommandSender<T> {
	@Autowired
	private FutureManager futureManager;

	public SFuture<T> send(T command) {
		doSend(command);
		SFuture<T> future = buildFuture(command);
		return futureManager.registFuture(command, future);
	}

	protected SFuture<T> buildFuture(T command) {
		return new DefaultFuture<T>();
	}

	protected abstract void doSend(T command);

	public void setFutureManager(FutureManager futureManager) {
		this.futureManager = futureManager;
	}

}
