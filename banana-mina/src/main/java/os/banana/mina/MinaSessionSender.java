/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import org.apache.mina.api.IoSession;

import os.banana.protocol.command.AbstractCommandSender;
import os.banana.protocol.command.Command;

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

	@Override
	protected void doSend(Command command) {
		session.write(command);
	}

	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

}
