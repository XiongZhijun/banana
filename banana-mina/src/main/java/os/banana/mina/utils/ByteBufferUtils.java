/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina.utils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public abstract class ByteBufferUtils {

	private static final String UTF_8 = "UTF-8";

	public static String toString(ByteBuffer buffer) {
		return toString(buffer, UTF_8);
	}

	public static String toString(ByteBuffer buffer, String charsetName) {
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes);
		try {
			return new String(bytes, charsetName);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("unsupported encoding type : "
					+ charsetName, e);
		}
	}

	public static ByteBuffer toByteBuffer(String message) {
		return toByteBuffer(message, UTF_8);
	}

	public static ByteBuffer toByteBuffer(String message, String charsetName) {
		byte[] bytes;
		try {
			bytes = message.getBytes(charsetName);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("unsupported encoding type : "
					+ charsetName, e);
		}
		ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
		byteBuffer.put(bytes);
		byteBuffer.flip();
		return byteBuffer;
	}
}
