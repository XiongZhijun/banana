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
public class BananaException extends RuntimeException {

	public BananaException() {
		super();
	}

	public BananaException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BananaException(String message, Throwable cause) {
		super(message, cause);
	}

	public BananaException(String message) {
		super(message);
	}

	public BananaException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 4920092925053840458L;

}
