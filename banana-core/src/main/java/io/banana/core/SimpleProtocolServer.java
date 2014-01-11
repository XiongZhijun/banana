/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com 
 */
package io.banana.core;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

/**
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class SimpleProtocolServer implements ProtocolServer {

	private ApplicationContext applicationContext;
	private ClassLoader classLoader;

	/**
	 * @param applicationContext
	 * @param classLoader
	 */
	public SimpleProtocolServer(ApplicationContext applicationContext,
			ClassLoader classLoader) {
		this.applicationContext = applicationContext;
		this.classLoader = classLoader;
	}

	public void start(ProtocolDefinition definition,
			Map<String, String> properties) {
		if (isInvalid(definition)) {
			throw new IllegalProtocolDefinitionException(definition);
		}
	}

	private boolean isInvalid(ProtocolDefinition definition) {
		return StringUtils.isEmpty(definition.getName())
				|| ArrayUtils.isEmpty(definition.getSpringLocations());
	}

	public void stop(ProtocolDefinition definition) {
		// TODO Auto-generated method stub

	}

}
