/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import org.apache.mina.api.AbstractIoHandler;
import org.apache.mina.api.IoService;
import org.apache.mina.api.IoSession;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class CommandHandler extends AbstractIoHandler {

	@Override
	public void sessionOpened(IoSession session) {
		// TODO Auto-generated method stub
		super.sessionOpened(session);
	}

	@Override
	public void sessionClosed(IoSession session) {
		// TODO Auto-generated method stub
		super.sessionClosed(session);
	}

	@Override
	public void messageReceived(IoSession session, Object message) {
		// TODO Auto-generated method stub
		super.messageReceived(session, message);
	}

	@Override
	public void serviceActivated(IoService service) {
		// TODO Auto-generated method stub
		super.serviceActivated(service);
	}

	@Override
	public void serviceInactivated(IoService service) {
		// TODO Auto-generated method stub
		super.serviceInactivated(service);
	}

	@Override
	public void exceptionCaught(IoSession session, Exception cause) {
		// TODO Auto-generated method stub
		super.exceptionCaught(session, cause);
	}

}
