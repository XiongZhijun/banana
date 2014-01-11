/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com 
 */
package io.banana.core;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@ContextConfiguration
public class ServerDefinitionTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private ServerDefinition serverDefinition;

	@Test
	public void testDependencies() throws IOException {
		assertNotNull(serverDefinition);
		Resource[] dependencies = serverDefinition.getDependencies();
		assertNotNull(dependencies);
		assertEquals(2, dependencies.length);
	}

}
