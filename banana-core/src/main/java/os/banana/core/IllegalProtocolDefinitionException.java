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
public class IllegalProtocolDefinitionException extends RuntimeException {

	public IllegalProtocolDefinitionException(ProtocolDefinition definition) {
		super("ProtocolDefiniton is illegal.");
	}

	private static final long serialVersionUID = 3867733810560034913L;

}
