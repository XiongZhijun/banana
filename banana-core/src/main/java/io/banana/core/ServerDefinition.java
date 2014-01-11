/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com 
 */
package io.banana.core;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

/**
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class ServerDefinition {

	private String name;
	private Resource[] dependencies;
	private String[] springLocations;
	private ClassLoader parentClassLoader;
	private ApplicationContext parentContext;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Resource[] getDependencies() {
		return dependencies;
	}

	public void setDependencies(Resource[] dependencies) {
		this.dependencies = dependencies;
	}

	public String[] getSpringLocations() {
		return springLocations;
	}

	public void setSpringLocations(String[] springLocations) {
		this.springLocations = springLocations;
	}

	public ClassLoader getParentClassLoader() {
		return parentClassLoader;
	}

	public void setParentClassLoader(ClassLoader parentClassLoader) {
		this.parentClassLoader = parentClassLoader;
	}

	public ApplicationContext getParentContext() {
		return parentContext;
	}

	public void setParentContext(ApplicationContext parentContext) {
		this.parentContext = parentContext;
	}

}
