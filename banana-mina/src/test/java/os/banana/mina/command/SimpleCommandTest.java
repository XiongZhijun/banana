/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina.command;

import static org.junit.Assert.*;

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

}
