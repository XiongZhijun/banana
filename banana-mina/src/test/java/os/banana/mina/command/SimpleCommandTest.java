/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import os.cherry.lang.StringUtils;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class SimpleCommandTest {

	@Test
	public void testGetId() {
		SimpleCommand command = new SimpleCommand();
		assertTrue(StringUtils.isBlank(command.getId()));
		String newCommandId = "newCommandId";
		command.setId(newCommandId);
		assertEquals(newCommandId, command.getId());
	}

	@Test
	public void testCreate() {
		String id = "id1";
		SimpleCommand command = new SimpleCommand(id);
		assertEquals(id, command.getId());
	}

}
