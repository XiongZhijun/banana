/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class DefaultFuture<V> extends AbstractFuture<V> {

	@Override
	protected boolean cancelOwner(boolean mayInterruptIfRunning) {
		return false;
	}

}
