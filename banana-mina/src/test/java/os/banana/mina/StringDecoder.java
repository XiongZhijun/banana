/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import java.nio.ByteBuffer;

import org.apache.mina.codec.StatelessProtocolDecoder;
import org.springframework.util.StringUtils;

import os.banana.mina.utils.ByteBufferUtils;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class StringDecoder implements
		StatelessProtocolDecoder<ByteBuffer, String> {

	public Void createDecoderState() {
		return null;
	}

	public String decode(ByteBuffer input, Void context) {
		String message = ByteBufferUtils.toString(input);
		return StringUtils.isEmpty(message) ? null : message;
	}

	public void finishDecode(Void context) {
	}

}
