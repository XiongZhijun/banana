/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com 
 */
package io.banana.core;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@ContextConfiguration
public class SimpleProtocolServerTest extends AbstractJUnit4SpringContextTests {

	@Test(expected = IllegalProtocolDefinitionException.class)
	public void testStartWithIllegalDefinition() {
		SimpleProtocolServer server = new SimpleProtocolServer(
				applicationContext);
		ProtocolDefinition definition = new ProtocolDefinition();
		server.start(definition);
	}

	@Test
	public void testStop() {
		fail("Not yet implemented");
	}

}
