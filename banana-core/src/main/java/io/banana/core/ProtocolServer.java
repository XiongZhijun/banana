/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com 
 */
package io.banana.core;

import java.util.Map;

/**
 * 定义了实现一个协议服务的规范，描述了一个协议服务的基本行为。
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public interface ProtocolServer {

	/**
	 * 根据协议定义启动一个协议服务。
	 * 
	 * @param definition
	 *            协议定义
	 */
	void start(ProtocolDefinition definition);

	/**
	 * 暂停协议服务。
	 */
	void suspend();

	/**
	 * 重新运行协议服务。
	 * 
	 * @param properties
	 */
	void resume(Map<String, String> properties);

	/**
	 * 停止当前协议服务
	 */
	void stop();
}
