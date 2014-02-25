/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol.frame;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@RunWith(Parameterized.class)
public class SimpleFrameCachePollTest {
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ new byte[0], new byte[0], new byte[0] },
				{ new byte[] { 0x23, 0x32, 0x03 }, new byte[] {},
						new byte[] { 0x23, 0x32, 0x03 } },
				{ new byte[] { 0x23, 0x23, 0x0D, 0x0A },
						new byte[] { 0x23, 0x23, 0x0D, 0x0A }, new byte[] {} },
				{ new byte[] { 0x23, 0x23, 0x36, 0x34, 0x0D, 0x0A },
						new byte[] { 0x23, 0x23, 0x36, 0x34, 0x0D, 0x0A },
						new byte[] {} },
				{
						new byte[] { 0x23, 0x23, 0x36, 0x34, 0x0D, 0x0A, 0x32,
								0x54 },
						new byte[] { 0x23, 0x23, 0x36, 0x34, 0x0D, 0x0A },
						new byte[] { 0x32, 0x54 } },
				{
						new byte[] { 0x44, 0x45, 0x23, 0x23, 0x36, 0x34, 0x0D,
								0x0A, 0x32, 0x54 },
						new byte[] { 0x23, 0x23, 0x36, 0x34, 0x0D, 0x0A },
						new byte[] { 0x32, 0x54 } },
				{
						new byte[] { 0x23, 0x23, 0x23, 0x0D, 0x0D, 0x0A, 0x23,
								0x23, 0x12, 0x65, 0x0D, 0x0A },
						new byte[] { 0x23, 0x23, 0x23, 0x0D, 0x0D, 0x0A },
						new byte[] { 0x23, 0x23, 0x12, 0x65, 0x0D, 0x0A } }, });
	}

	private byte[] frame;
	private byte[] remaining;
	private SimpleFrameCache cache;

	public SimpleFrameCachePollTest(byte[] bytes, byte[] frame, byte[] remaining) {
		this.frame = frame;
		this.remaining = remaining;
		cache = new SimpleFrameCache();
		cache.setHead(new byte[] { 0x23, 0x23 });
		cache.setTail(new byte[] { 0x0D, 0x0A });
		cache.add(bytes);
	}

	@Test
	public void testPoll() {
		assertArrayEquals(frame, cache.poll());
		assertArrayEquals(remaining, cache.remaining());
	}
}
