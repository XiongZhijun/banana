/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol;

import java.util.concurrent.Future;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public interface SFuture<V> extends Future<V> {

	SFuture<V> register(FutureListener<V> listener);

	boolean isTimeout();

	interface FutureListener<V> {
		void exception(Throwable t);

		void completed(V result);
	}
}
