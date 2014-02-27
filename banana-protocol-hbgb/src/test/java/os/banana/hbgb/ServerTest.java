/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.hbgb;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import os.banana.mina.SimpleProtocolCodec;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@ContextConfiguration
public class ServerTest extends AbstractJUnit4SpringContextTests {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws InterruptedException {
		Thread.sleep(1000000);
	}

}
