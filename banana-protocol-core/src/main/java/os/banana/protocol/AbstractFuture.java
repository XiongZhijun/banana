/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package os.banana.protocol;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractFuture<V> implements SFuture<V> {

	private static final int FIVE_MINUTES = 5;

	private static final Log log = LogFactory.getLog(AbstractFuture.class);

	private final CountDownLatch latch = new CountDownLatch(1);

	private final List<FutureListener<V>> listeners = new ArrayList<FutureListener<V>>();

	private final AtomicReference<Object> result = new AtomicReference<Object>();

	private final Date createDate = new Date();
	private int timeout = FIVE_MINUTES;

	@SuppressWarnings({ "unchecked" })
	public SFuture<V> register(FutureListener<V> listener) {

		synchronized (latch) {
			if (!isDone()) {
				log.debug("future is not done, adding listener to listener set");
				listeners.add(listener);
				listener = null;
			}
		}

		if (listener != null) {
			log.debug("future is done calling listener");
			Object object = result.get();

			if (object instanceof Throwable) {
				scheduleException(listener, (Throwable) object);
			} else {
				scheduleResult(listener, (V) object);
			}
		}

		return this;
	}

	public boolean cancel(boolean mayInterruptIfRunning) {
		log.debug("Attempting to cancel");
		CancellationException ce = null;
		synchronized (latch) {
			if (!isCancelled() && !isDone()
					&& cancelOwner(mayInterruptIfRunning)) {

				log.debug("Successfully cancelled");

				ce = new CancellationException();
				result.set(ce);
			} else {
				log.debug("Unable to cancel");
			}

			latch.countDown();
		}

		if (ce != null) {
			log.debug("Calling listeners");

			for (FutureListener<V> listener : listeners) {
				scheduleException(listener, ce);
			}
		}

		return ce != null;
	}

	public boolean isCancelled() {
		return result.get() instanceof CancellationException;
	}

	public boolean isDone() {
		return latch.getCount() == 0;
	}

	@SuppressWarnings({ "unchecked" })
	public V get() throws InterruptedException, ExecutionException {

		log.trace("Entering wait");
		latch.await();
		log.trace("Wait completed");

		if (isCancelled()) {
			throw new CancellationException();
		}

		Object object = result.get();

		if (object instanceof ExecutionException) {
			throw (ExecutionException) object;
		} else {
			return (V) object;
		}
	}

	@SuppressWarnings({ "unchecked" })
	public V get(long timeout, TimeUnit unit) throws InterruptedException,
			ExecutionException, TimeoutException {

		log.trace("Entering wait");

		if (!latch.await(timeout, unit)) {
			throw new TimeoutException();
		}

		log.trace("Wait completed");

		if (isCancelled()) {
			throw new CancellationException();
		}

		Object object = result.get();

		if (object instanceof ExecutionException) {
			throw (ExecutionException) object;
		} else {
			return (V) object;
		}
	}

	protected abstract boolean cancelOwner(boolean mayInterruptIfRunning);

	protected void scheduleResult(FutureListener<V> listener, V result) {
		log.debug("Calling the default result scheduler");
		try {
			listener.completed(result);
		} catch (Exception e) {
			log.warn("Listener threw an exception", e);
		}
	}

	protected void scheduleException(FutureListener<V> listener,
			Throwable throwable) {
		log.debug("Calling the default exception scheduler");

		try {
			listener.exception(throwable);
		} catch (Exception e) {
			log.warn("Listener threw an exception", e);
		}
	}

	public final void setResult(V value) {
		assert !isDone();

		synchronized (latch) {
			result.set(value);
			latch.countDown();
		}

		for (FutureListener<V> listener : listeners) {
			scheduleResult(listener, value);
		}

		listeners.clear();
	}

	protected final void setException(Throwable t) {
		assert !isDone();

		ExecutionException ee = new ExecutionException(t);

		synchronized (latch) {
			result.set(ee);
			latch.countDown();
		}

		for (FutureListener<V> listener : listeners) {
			scheduleException(listener, ee);
		}

		listeners.clear();
	}

	public boolean isTimeout() {
		long millis = TimeUnit.MINUTES.toMillis(timeout);
		return System.currentTimeMillis() - createDate.getTime() > millis;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

}
