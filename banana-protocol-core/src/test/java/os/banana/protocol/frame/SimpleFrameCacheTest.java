/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol.frame;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class SimpleFrameCacheTest {
	private SimpleFrameCache cache;

	@Before
	public void setUp() {
		cache = new SimpleFrameCache();
		cache.setHead(new byte[] { 0x23, 0x23 }).setTail(
				new byte[] { 0x0D, 0x0A });
	}

	@Test
	public void testHasNext() {
		assertFalse(cache.hasNext());
		cache.add(new byte[] { 0x23, 0x23, 0x0D, 0x0A });
		assertTrue(cache.hasNext());
	}

	@Test
	public void testClear() {
		assertEquals(0, cache.size());
		cache.add(new byte[] { 0x23, 0x23, 0x0D, 0x0A });
		assertEquals(4, cache.size());
		cache.clear();
		assertEquals(0, cache.size());
	}

	@Test
	public void testPeek() {
		cache.add(new byte[] { 0x0D, 0x23, 0x23, 0x34, 0x23, 0x0D, 0x0A, 0x23 });
		assertArrayEquals(new byte[] { 0x0D, 0x23, 0x23, 0x34, 0x23, 0x0D,
				0x0A, 0x23 }, cache.remaining());
		assertArrayEquals(new byte[] { 0x34, 0x23 }, cache.peek());
		assertArrayEquals(new byte[] { 0x0D, 0x23, 0x23, 0x34, 0x23, 0x0D,
				0x0A, 0x23 }, cache.remaining());
	}

}
