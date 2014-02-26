/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@RunWith(EasyMockRunner.class)
public class AbstractServerTest {

	@TestSubject
	private AbstractServer server = new TestServer();
	@Mock
	private TerminalSession terminalSession1;
	@Mock
	private TerminalSession terminalSession2;
	@Mock
	private TerminalSession terminalSession3;

	@Test
	public void testRegistTerminal() {
		expect(terminalSession1.getCode()).andReturn("terminal1");
		replay(terminalSession1);
		assertNull(server.getTerminalSession("terminal1"));
		server.registTerminal(terminalSession1);
		assertEquals(terminalSession1, server.getTerminalSession("terminal1"));
		verify(terminalSession1);
	}

	@Test
	public void testUnregistTerminal() {
		expect(terminalSession1.getCode()).andReturn("terminal1");
		replay(terminalSession1);
		server.registTerminal(terminalSession1);
		assertEquals(terminalSession1, server.getTerminalSession("terminal1"));
		server.unregistTerminal("terminal1");
		assertNull(server.getTerminalSession("terminal1"));
		verify(terminalSession1);
	}

	@Test
	public void testGetAll() {
		expect(terminalSession1.getCode()).andReturn("t1");
		expect(terminalSession2.getCode()).andReturn("t2");
		expect(terminalSession3.getCode()).andReturn("t3");

		replay(terminalSession1, terminalSession2, terminalSession3);
		assertArrayEquals(new Object[] {}, server.getAllTerminals().toArray());
		server.registTerminal(terminalSession1);
		assertArrayEquals(new Object[] { "t1" }, server.getAllTerminals()
				.toArray());
		server.registTerminal(terminalSession2);
		server.registTerminal(terminalSession3);
		assertEquals(3, server.getAllTerminals().size());
		assertTrue(server.getAllTerminals().contains("t1"));
		assertTrue(server.getAllTerminals().contains("t2"));
		assertTrue(server.getAllTerminals().contains("t3"));
		verify(terminalSession1, terminalSession2, terminalSession3);
	}

	class TestServer extends AbstractServer {

		public String getName() {
			return null;
		}

		public String getCode() {
			return null;
		}

		public void start() {
		}

		public void stop() {
		}

		public boolean isRunning() {
			return false;
		}

		public boolean isStopped() {
			return false;
		}

	}

}
