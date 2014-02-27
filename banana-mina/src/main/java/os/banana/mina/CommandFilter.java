/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import org.apache.mina.api.AbstractIoFilter;
import org.apache.mina.api.IoSession;
import org.apache.mina.filterchain.ReadFilterChainController;
import org.apache.mina.filterchain.WriteFilterChainController;
import org.apache.mina.session.WriteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;

import os.banana.protocol.CommandDecoder;
import os.banana.protocol.CommandEncoder;
import os.banana.protocol.Priorities;
import os.banana.protocol.command.Command;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class CommandFilter extends AbstractIoFilter implements Ordered {
	@Autowired
	private CommandEncoder encoder;
	@Autowired
	private CommandDecoder decoder;
	private int order = Priorities.LEVEL_1;

	@Override
	public void messageReceived(IoSession session, Object message,
			ReadFilterChainController controller) {
		Command command = decoder.decode((byte[]) message);
		super.messageReceived(session, command, controller);
	}

	@Override
	public void messageWriting(IoSession session, WriteRequest message,
			WriteFilterChainController controller) {
		byte[] bytes = encoder.encode((Command) message.getMessage());
		message.setMessage(bytes);
		super.messageWriting(session, message, controller);
	}

	public void setEncoder(CommandEncoder encoder) {
		this.encoder = encoder;
	}

	public void setDecoder(CommandDecoder decoder) {
		this.decoder = decoder;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

}
