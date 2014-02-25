/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol.frame;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public interface FrameCache {

	boolean hasNext();

	byte[] poll();

	byte[] peek();

	byte[] remaining();

	FrameCache add(byte[] bytes);

	int size();

	void clear();
}
