/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com 
 */
package io.banana.core;

import java.util.Map;

/**
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public interface ProtocolServer {

	void start(ProtocolDefinition definition, Map<String, String> properties);

	void stop(ProtocolDefinition definition);
}
