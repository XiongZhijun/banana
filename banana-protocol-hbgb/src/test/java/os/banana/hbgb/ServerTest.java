/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.hbgb;

import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@ContextConfiguration
public class ServerTest extends AbstractJUnit4SpringContextTests {

	private ClassPathXmlApplicationContext context;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(applicationContext);
		PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
		Properties properties = new Properties();
		properties.put("server.name", "环保国标");
		properties.put("server.code", "hbgb");
		properties.put("server.port", "19999");
		cfg.setProperties(properties);
		context.addBeanFactoryPostProcessor(cfg);
		context.setConfigLocation("classpath:META-INF/banana-protocol-hbgb.xml");
		context.refresh();
	}

	@After
	public void tearDown() {
		context.close();
	}

	@Test
	public void test() throws InterruptedException {
		Thread.sleep(1000000);
	}

}
