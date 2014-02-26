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
public class ServerManagerTest {
	@TestSubject
	private ServerManager manager;
	@Mock
	private Server server1;
	@Mock
	private Server server2;

	@Before
	public void setUp() throws Exception {
		manager = new ServerManager();
	}

	@Test
	public void testRegist() {
		expect(server1.getCode()).andReturn("server1").times(2);
		expect(server2.getCode()).andReturn("server1");
		server1.stop();

		replay(server1, server2);
		assertNull(manager.getServer("server1"));
		manager.regist(server1);
		assertEquals(server1, manager.getServer("server1"));
		manager.regist(server1);
		assertEquals(server1, manager.getServer("server1"));
		manager.regist(server2);
		assertEquals(server2, manager.getServer("server1"));
		verify(server1, server2);
	}

	@Test
	public void testUnregist() {
		expect(server1.getCode()).andReturn("server1");
		server1.stop();
		replay(server1);
		manager.regist(server1);
		assertEquals(server1, manager.getServer("server1"));
		manager.unregist("server1");
		assertNull(manager.getServer("server1"));
		verify(server1);
	}

	@Test
	public void testGetAllServers() {
		expect(server1.getCode()).andReturn("server1");
		expect(server2.getCode()).andReturn("server2");
		replay(server1, server2);
		assertArrayEquals(new Object[] {}, manager.getAllServers().toArray());
		manager.regist(server1);
		assertArrayEquals(new Object[] { server1 }, manager.getAllServers()
				.toArray());
		manager.regist(server2);
		assertArrayEquals(new Object[] { server1, server2 }, manager
				.getAllServers().toArray());
		verify(server1, server2);
	}

}
