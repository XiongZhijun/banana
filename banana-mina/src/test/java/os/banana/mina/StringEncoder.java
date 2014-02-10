/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import java.nio.ByteBuffer;

import org.apache.mina.codec.StatelessProtocolEncoder;

import os.banana.mina.utils.ByteBufferUtils;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class StringEncoder implements
		StatelessProtocolEncoder<String, ByteBuffer> {

	public Void createEncoderState() {
		return null;
	}

	public ByteBuffer encode(String message, Void context) {
		return ByteBufferUtils.toByteBuffer(message);
	}

}
