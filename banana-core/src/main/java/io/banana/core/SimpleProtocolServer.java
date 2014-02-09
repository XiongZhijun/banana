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
	 */
	public SimpleProtocolServer(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		this.classLoader = applicationContext.getClassLoader();
	}

	public void start(ProtocolDefinition definition) {
		if (isInvalid(definition)) {
			throw new IllegalProtocolDefinitionException(definition);
		}
	}

	private boolean isInvalid(ProtocolDefinition definition) {
		return StringUtils.isEmpty(definition.getName())
				|| ArrayUtils.isEmpty(definition.getSpringLocations());
	}

	public void suspend() {
		// TODO Auto-generated method stub

	}

	public void resume(Map<String, String> properties) {
		// TODO Auto-generated method stub

	}

	public void stop() {
		// TODO Auto-generated method stub

	}

}
