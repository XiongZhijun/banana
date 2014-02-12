/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class DefaultStrategyManager {

	private Properties defaultStrategies;
	private ApplicationContext context;
	private DefaultStrategyManager parent;

	public DefaultStrategyManager(ApplicationContext context,
			String strategiesPath) {
		this(context, strategiesPath, null);
	}

	public DefaultStrategyManager(ApplicationContext context,
			String strategiesPath, DefaultStrategyManager parent) {
		try {
			Resource resource = context.getResource(strategiesPath);
			defaultStrategies = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException ex) {
			throw new IllegalStateException("Could not load '" + strategiesPath
					+ "' : " + ex.getMessage());
		}
		this.context = context;
		this.parent = parent;
	}

	public <T> T getDefaultStrategy(Class<T> strategyInterface) {
		List<T> strategies = getDefaultStrategies(strategyInterface);
		if (strategies.size() != 1) {
			throw new BeanInitializationException(
					"Manager needs exactly 1 strategy for interface ["
							+ strategyInterface.getName() + "]");
		}
		return strategies.get(0);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getDefaultStrategies(Class<T> strategyInterface) {
		String key = strategyInterface.getName();
		String value = defaultStrategies.getProperty(key);
		if (value != null) {
			String[] classNames = StringUtils
					.commaDelimitedListToStringArray(value);
			List<T> strategies = new ArrayList<T>(classNames.length);
			for (String className : classNames) {
				try {
					Class<?> clazz = ClassUtils.forName(className,
							context.getClassLoader());
					Object strategy = createDefaultStrategy(context, clazz);
					strategies.add((T) strategy);
				} catch (ClassNotFoundException ex) {
					throw new BeanInitializationException(
							"Could not find default strategy class ["
									+ className + "] for interface [" + key
									+ "]", ex);
				} catch (LinkageError err) {
					throw new BeanInitializationException(
							"Error loading default strategy class ["
									+ className
									+ "] for interface ["
									+ key
									+ "]: problem with class file or dependent class",
							err);
				}
			}
			return strategies;
		} else if (this.parent != null) {
			return parent.getDefaultStrategies(strategyInterface);
		} else {
			return new LinkedList<T>();
		}
	}

	protected Object createDefaultStrategy(ApplicationContext context,
			Class<?> clazz) {
		return context.getAutowireCapableBeanFactory().createBean(clazz);
	}
}
