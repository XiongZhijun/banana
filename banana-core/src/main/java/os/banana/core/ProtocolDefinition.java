/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com 
 */
package os.banana.core;

/**
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class ProtocolDefinition {

	private String name;
	private String[] dependencyURLs;
	private String[] springLocations;
	private boolean autoStart;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getDependencyURLs() {
		return dependencyURLs;
	}

	public void setDependencyURLs(String[] dependencyURLs) {
		this.dependencyURLs = dependencyURLs;
	}

	public String[] getSpringLocations() {
		return springLocations;
	}

	public void setSpringLocations(String[] springLocations) {
		this.springLocations = springLocations;
	}

	public boolean isAutoStart() {
		return autoStart;
	}

	public void setAutoStart(boolean autoStart) {
		this.autoStart = autoStart;
	}

}
