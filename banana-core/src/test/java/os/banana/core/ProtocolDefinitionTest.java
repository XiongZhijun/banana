/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com 
 */
package os.banana.core;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import os.banana.core.ProtocolDefinition;

/**
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class ProtocolDefinitionTest {

	private ProtocolDefinition definition;

	@Before
	public void setUp() {
		definition = new ProtocolDefinition();
	}

	@Test
	public void testNew() {
		definition.setName("SEPA-GAS");
		definition.setDependencyURLs(new String[] { "sepa-gas.jar",
				"sepa-core.jar" });
		definition.setSpringLocations(new String[] { "sepa-gas-context.xml" });
		definition.setAutoStart(true);
		assertEquals("SEPA-GAS", definition.getName());
		assertArrayEquals(new String[] { "sepa-gas.jar", "sepa-core.jar" },
				definition.getDependencyURLs());
		assertArrayEquals(new String[] { "sepa-gas-context.xml" },
				definition.getSpringLocations());
		assertTrue(definition.isAutoStart());
	}
}
