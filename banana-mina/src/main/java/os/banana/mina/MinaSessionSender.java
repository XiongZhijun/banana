/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import org.apache.mina.api.IoSession;

import os.banana.mina.command.AbstractCommandSender;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class MinaSessionSender extends AbstractCommandSender {

	private IoSession session;

	public MinaSessionSender() {
	}

	public MinaSessionSender(IoSession session) {
		this.session = session;
	}

	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

}
