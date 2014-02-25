/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;

import os.banana.protocol.frame.FrameCache;
import os.banana.protocol.frame.SimpleFrameCache;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class SimpleProtocolCodecTest {
	private SimpleProtocolCodec codec;

	@Before
	public void setUp() {
		codec = new SimpleProtocolCodec();
		codec.setHead(new byte[] { 0x23, 0x23 });
		codec.setTail(new byte[] { 0x0D, 0x0A });
	}

	@Test
	public void testCreateEncoderState() {
		assertNull(codec.createEncoderState());
	}

	@Test
	public void testCreateDecoderState() {
		SimpleFrameCache cache = (SimpleFrameCache) codec.createDecoderState();
		assertArrayEquals(new byte[] { 0x23, 0x23 }, cache.getHead());
		assertArrayEquals(new byte[] { 0x0D, 0x0A }, cache.getTail());
	}

	@Test
	public void testEncode() {
		byte[] message = new byte[] { 0x11, 0x12, 0x23, 0x34 };
		ByteBuffer buffer = codec.encode(message, null);
		assertArrayEquals(message, buffer.array());
	}

	@Test
	public void testEncodeByNull() {
		ByteBuffer buffer = codec.encode(null, null);
		assertArrayEquals(new byte[0], buffer.array());
	}

	@Test
	public void testDecode() {
		FrameCache frameCache = codec.createDecoderState();
		byte[] message = new byte[] { 0x34, 0x25, 0x23, 0x23, 0x33, 0x44, 0x0D,
				0x0A, 0x12 };
		ByteBuffer input = ByteBuffer.allocate(message.length);
		input.put(message);
		input.flip();
		byte[] array = codec.decode(input, frameCache);
		assertArrayEquals(new byte[] { 0x23, 0x23, 0x33, 0x44, 0x0D, 0x0A },
				array);
	}

	@Test
	public void testFinishDecode() {
		IMocksControl control = EasyMock.createControl();
		FrameCache cache = control.createMock(FrameCache.class);
		cache.clear();
		
		EasyMock.replay(cache);
		codec.finishDecode(cache);
		EasyMock.verify(cache);
	}

}