/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina;

import org.apache.mina.codec.ProtocolDecoder;
import org.apache.mina.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.core.Ordered;

import os.banana.protocol.Priorities;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@SuppressWarnings("rawtypes")
public class ProtocolCodecFilterFactoryBean extends
		AbstractFactoryBean<ProtocolCodecFilter> implements Ordered {

	@Autowired
	private ProtocolEncoder<?, ?, ?> encoder;
	@Autowired
	private ProtocolDecoder<?, ?, ?> decoder;

	@Override
	public Class<?> getObjectType() {
		return ProtocolCodecFilter.class;
	}

	@Override
	protected ProtocolCodecFilter createInstance() throws Exception {
		return new OrderedProtocolCodecFilter(encoder, decoder);
	}

	public int getOrder() {
		return 0;
	}

	class OrderedProtocolCodecFilter extends ProtocolCodecFilter implements
			Ordered {

		@SuppressWarnings("unchecked")
		public OrderedProtocolCodecFilter(ProtocolEncoder encoder,
				ProtocolDecoder decoder) {
			super(encoder, decoder);
		}

		public int getOrder() {
			return Priorities.LEVEL_0;
		}

	}

}
