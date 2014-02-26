/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import os.cherry.lang.StringUtils;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class SimpleCommandTest {

	private SimpleCommand command;

	@Before
	public void setUp() {
		command = new SimpleCommand();
	}

	@Test
	public void testGetId() {
		assertTrue(StringUtils.isBlank(command.getId()));
		String newCommandId = "newCommandId";
		command.setId(newCommandId);
		assertEquals(newCommandId, command.getId());
	}

	@Test
	public void testGetTerminalCode() {
		assertTrue(StringUtils.isBlank(command.getTerminalCode()));
		String terminalCode = "terminal1";
		command.setTerminalCode(terminalCode);
		assertEquals(terminalCode, command.getTerminalCode());
	}

	@Test
	public void testGetContent() {
		Object content = new Object();
		command.setContent(content);
		assertEquals(content, command.getContent());
	}

	@Test
	public void testCreate() {
		String id = "id1";
		SimpleCommand command1 = new SimpleCommand(id);
		assertEquals(id, command1.getId());

		Object content = new Object();
		SimpleCommand command2 = new SimpleCommand(id, content);
		assertEquals(id, command2.getId());
		assertEquals(content, command2.getContent());
	}

	@Test
	public void testSerialNumber() {
		command.setId("id1");
		assertEquals("id1", command.buildSendSerialNumber());
		assertEquals("id1", command.getSendedSerialNumber());
	}

}
