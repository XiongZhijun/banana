/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import java.util.Map;

import org.apache.mina.api.IoFilter;
import org.apache.mina.codec.ProtocolDecoder;
import org.apache.mina.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.OrderComparator;

import os.cherry.lang.ArrayUtils;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class IoFiltersFactoryBean extends AbstractFactoryBean<IoFilter[]>
		implements InitializingBean, ApplicationContextAware {

	@Autowired
	private ProtocolEncoder<?, ?, ?> encoder;
	@Autowired
	private ProtocolDecoder<?, ?, ?> decoder;
	private ApplicationContext applicationContext;

	@Override
	public Class<?> getObjectType() {
		return IoFilter[].class;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected IoFilter[] createInstance() throws Exception {
		ProtocolCodecFilter protocolCodecFilter = new ProtocolCodecFilter(
				encoder, decoder);
		Map<String, IoFilter> filterMap = applicationContext
				.getBeansOfType(IoFilter.class);
		IoFilter[] filters = filterMap.values().toArray(new IoFilter[0]);
		OrderComparator.sort(filters);
		return ArrayUtils.add(IoFilter.class, protocolCodecFilter, filters);
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
