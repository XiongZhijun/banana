/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.api.IoFilter;
import org.apache.mina.api.IoHandler;
import org.apache.mina.transport.tcp.TcpSessionConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class MinaTcpServerLoader implements BeanPostProcessor,
		ApplicationContextAware {

	private ApplicationContext applicationContext;

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean instanceof MinaTcpServer) {
			init((MinaTcpServer) bean);
		}
		return bean;
	}

	private void init(MinaTcpServer server) {
		IoHandler handler = applicationContext.getBean(IoHandler.class);
		server.setHandler(handler);
		try {
			IoFilter[] filters = applicationContext.getBean(IoFilter[].class);
			server.setFilters(filters);
		} catch (NoSuchBeanDefinitionException e) {
			log.warn("no filter exist.");
		}
		try {
			TcpSessionConfig sessionConfig = applicationContext
					.getBean(TcpSessionConfig.class);
			server.setSessionConfig(sessionConfig);
		} catch (NoSuchBeanDefinitionException e) {
			log.warn("no tcp session config exist.");
		}

	}

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	private static Log log = LogFactory.getLog(MinaTcpServerLoader.class);
}
