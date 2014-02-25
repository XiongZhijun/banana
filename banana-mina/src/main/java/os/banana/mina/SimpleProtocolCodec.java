/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import java.nio.ByteBuffer;

import org.apache.mina.codec.ProtocolDecoder;
import org.apache.mina.codec.ProtocolEncoder;

import os.banana.protocol.frame.FrameCache;
import os.banana.protocol.frame.SimpleFrameCache;
import os.cherry.lang.ArrayUtils;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class SimpleProtocolCodec implements
		ProtocolDecoder<ByteBuffer, byte[], FrameCache>,
		ProtocolEncoder<byte[], ByteBuffer, Void> {

	private byte[] head;
	private byte[] tail;
	private int maxSize = 1024 * 4;

	public Void createEncoderState() {
		return null;
	}

	public ByteBuffer encode(byte[] message, Void context) {
		if (message == null) {
			message = ArrayUtils.EMPTY_BYTE_ARRAY;
		}
		ByteBuffer buffer = ByteBuffer.allocate(message.length);
		buffer.put(message);
		buffer.flip();
		return buffer;
	}

	public FrameCache createDecoderState() {
		SimpleFrameCache cache = new SimpleFrameCache();
		cache.setHead(head);
		cache.setTail(tail);
		return cache;
	}

	public byte[] decode(ByteBuffer input, FrameCache frameCache) {
		int remaining = input.remaining();
		if (remaining + frameCache.size() > maxSize) {
			frameCache.clear();
		}
		byte[] array = new byte[input.remaining()];
		input.get(array);
		frameCache.add(array);

		if (frameCache.hasNext()) {
			return frameCache.poll();
		}
		return null;
	}

	public void finishDecode(FrameCache cache) {
		cache.clear();
	}

	public void setHead(byte[] head) {
		this.head = head;
	}

	public void setTail(byte[] tail) {
		this.tail = tail;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

}
