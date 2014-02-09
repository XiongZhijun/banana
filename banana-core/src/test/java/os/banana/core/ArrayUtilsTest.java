/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com 
 */
package os.banana.core;

import static org.junit.Assert.*;

import org.junit.Test;

import os.banana.core.ArrayUtils;

/**
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class ArrayUtilsTest {

	/**
	 * Test method for
	 * {@link os.banana.core.ArrayUtils#isEmpty(java.lang.String[])}.
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(ArrayUtils.isEmpty(new String[0]));
		assertTrue(ArrayUtils.isEmpty(null));
		assertFalse(ArrayUtils.isEmpty(new String[] { "haha" }));
		assertFalse(ArrayUtils.isEmpty(new String[] { "haha", "hehe" }));
		assertFalse(ArrayUtils.isEmpty(new String[] { null, null }));
	}
}
