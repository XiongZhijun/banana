/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import static org.junit.Assert.*;

import org.apache.mina.api.AbstractIoFilter;
import org.apache.mina.api.IoFilter;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@ContextConfiguration
public class ProtocolCodecFilterFactoryBeanTest extends
		AbstractJUnit4SpringContextTests {
	@Autowired
	private TestFilter1 filter1;
	@Autowired
	private TestFilter2 filter2;
	@Autowired
	private IoFilter[] filters;

	@Test
	public void testCreateInstance() {
		assertEquals(3, filters.length);
		assertTrue(filters[0] instanceof ProtocolCodecFilter);
		assertEquals(filter1, filters[1]);
		assertEquals(filter2, filters[2]);
	}

	public static class TestFilter1 extends AbstractIoFilter implements Ordered {
		public int getOrder() {
			return 1;
		}

	}

	public static class TestFilter2 extends AbstractIoFilter implements Ordered {
		public int getOrder() {
			return 2;
		}

	}

}
