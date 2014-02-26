/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol.command;

import org.springframework.beans.factory.annotation.Autowired;

import os.banana.protocol.DefaultFuture;
import os.banana.protocol.FutureManager;
import os.banana.protocol.SFuture;
import os.banana.protocol.Server;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public abstract class AbstractResponseController<T extends Command> implements
		CommandController<T> {
	@Autowired
	private FutureManager futureManager;

	public void handle(T response, CommandSender sender, Server server) {
		SFuture<T> future = futureManager.getFuture(response);
		if (future == null) {
			return;
		}
		doResponse(future, response, sender, server);
	}

	protected void doResponse(SFuture<T> future, T response,
			CommandSender sender, Server server) {
		if (future instanceof DefaultFuture) {
			((DefaultFuture<T>) future).setResult(response);
		}
	}

	public void setFutureManager(FutureManager futureManager) {
		this.futureManager = futureManager;
	}
}
