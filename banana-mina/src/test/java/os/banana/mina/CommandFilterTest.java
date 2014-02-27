/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.apache.mina.api.IoSession;
import org.apache.mina.filterchain.ReadFilterChainController;
import org.apache.mina.filterchain.WriteFilterChainController;
import org.apache.mina.session.DefaultWriteRequest;
import org.apache.mina.session.WriteRequest;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import os.banana.protocol.CommandDecoder;
import os.banana.protocol.CommandEncoder;
import os.banana.protocol.command.Command;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@RunWith(EasyMockRunner.class)
public class CommandFilterTest {
	@TestSubject
	private CommandFilter filter = new CommandFilter();
	@Mock
	private CommandDecoder decoder;
	@Mock
	private CommandEncoder encoder;
	@Mock
	private IoSession session;
	@Mock
	private ReadFilterChainController readController;
	@Mock
	private WriteFilterChainController writeController;
	@Mock
	private Command command;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMessageReceivedIoSessionObjectReadFilterChainController() {
		byte[] message = new byte[] { 0x00 };
		expect(decoder.decode(message)).andReturn(command);
		readController.callReadNextFilter(command);
		replay(decoder, readController);
		filter.messageReceived(session, message, readController);
		verify(decoder, readController);
	}

	@Test
	public void testMessageWritingIoSessionWriteRequestWriteFilterChainController() {
		WriteRequest message = new DefaultWriteRequest(command);
		byte[] bytes = new byte[] { 0x00 };
		expect(encoder.encode(command)).andReturn(bytes);
		writeController.callWriteNextFilter(message);
		replay(encoder, writeController);
		filter.messageWriting(session, message, writeController);
		verify(encoder, writeController);
	}

}
