/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol;

import static org.junit.Assert.*;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.easymock.EasyMock.*;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@RunWith(EasyMockRunner.class)
public class TerminalManagerTest {
	@TestSubject
	private TerminalManager terminalManager;
	@Mock
	private TerminalSession terminalSession;

	@Before
	public void setUp() throws Exception {
		terminalManager = new TerminalManager();
	}

	@Test
	public void testRegist() {
		expect(terminalSession.getCode()).andReturn("terminal1");
		replay(terminalSession);
		assertNull(terminalManager.get("terminal1"));
		terminalManager.regist(terminalSession);
		assertEquals(terminalSession, terminalManager.get("terminal1"));
		assertNull(terminalManager.get("terminal2"));
		verify(terminalSession);
	}

	@Test
	public void testUnregist() {
		expect(terminalSession.getCode()).andReturn("terminal1");
		replay(terminalSession);
		terminalManager.regist(terminalSession);
		assertEquals(terminalSession, terminalManager.get("terminal1"));
		terminalManager.unregist("terminal1");
		assertNull(terminalManager.get("terminal1"));
		verify(terminalSession);
	}

}
