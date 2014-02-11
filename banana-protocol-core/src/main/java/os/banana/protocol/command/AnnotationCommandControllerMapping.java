/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.OrderComparator;

import os.cherry.lang.ArrayUtils;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class AnnotationCommandControllerMapping implements
		CommandControllerMapping, ApplicationContextAware {
	private Map<String, CommandController<?>> controllerMap = new HashMap<String, CommandController<?>>();

	@SuppressWarnings("unchecked")
	public <T extends Command> CommandController<T> findCommandController(
			T command) {
		String commandId = command.getId();
		CommandController<?> controller = controllerMap.get(commandId);
		return (CommandController<T>) (controller == null ? CommandController.EMPTY_COMMAND_CONTROLLER
				: controller);
	}

	@SuppressWarnings("rawtypes")
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		List<CommandController> values = findControllers(applicationContext);
		for (CommandController controller : values) {
			Class<? extends CommandController> controllerClass = controller
					.getClass();
			RequestId annotation = controllerClass
					.getAnnotation(RequestId.class);
			if (annotation == null || ArrayUtils.isEmpty(annotation.value())) {
				log.warn(controllerClass.getName() + " do not have annotation "
						+ RequestId.class.getName()
						+ ", or annotation's value(command id) is empty.");
				continue;
			}
			String[] ids = annotation.value();
			for (String id : ids) {
				controllerMap.put(id, controller);
			}
		}

	}

	@SuppressWarnings("rawtypes")
	private List<CommandController> findControllers(
			ApplicationContext applicationContext) {
		Map<String, CommandController> beans = applicationContext
				.getBeansOfType(CommandController.class);
		List<CommandController> values = new ArrayList<CommandController>(
				beans.values());
		OrderComparator.sort(values);
		return values;
	}

	private static Log log = LogFactory
			.getLog(AnnotationCommandControllerMapping.class);

}
