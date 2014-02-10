/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina.utils;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.Test;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class ByteBufferUtilsTest {

	@Test
	public void testConvertWithString() {
		ByteBuffer buffer = ByteBufferUtils.toByteBuffer("hello");
		assertEquals("hello", ByteBufferUtils.toString(buffer));
		
		buffer = ByteBufferUtils.toByteBuffer("中文和English");
		assertEquals("中文和English", ByteBufferUtils.toString(buffer));
	}

}
