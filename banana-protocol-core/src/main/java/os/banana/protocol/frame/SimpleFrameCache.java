/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol.frame;

import os.cherry.lang.ArrayUtils;
import os.cherry.lang.ByteArray;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class SimpleFrameCache implements FrameCache {
	private ByteArray array = new ByteArray();
	/** 包头 */
	private byte[] head;
	/** 包尾 */
	private byte[] tail;

	public boolean hasNext() {
		int startIndex = array.indexOf(head);
		if (startIndex < 0) {
			return false;
		}
		int endIndex = array.indexOf(tail, startIndex + head.length);
		return endIndex > 0;
	}

	public byte[] poll() {
		int startIndex = array.indexOf(head);
		if (startIndex < 0) {
			return ArrayUtils.EMPTY_BYTE_ARRAY;
		}
		int endIndex = array.indexOf(tail, startIndex + head.length);
		if (endIndex < 0) {
			return ArrayUtils.EMPTY_BYTE_ARRAY;
		}
		byte[] removed = array.remove(startIndex + head.length, endIndex);
		array.remove(0, startIndex + head.length + tail.length);
		return removed;
	}

	public byte[] peek() {
		int startIndex = array.indexOf(head);
		if (startIndex < 0) {
			return ArrayUtils.EMPTY_BYTE_ARRAY;
		}
		int endIndex = array.indexOf(tail, startIndex + head.length);
		if (endIndex < 0) {
			return ArrayUtils.EMPTY_BYTE_ARRAY;
		}
		return array.get(startIndex + head.length, endIndex);
	}

	public byte[] remaining() {
		return array.toArray();
	}

	public FrameCache add(byte[] bytes) {
		array.addAll(bytes);
		return this;
	}

	public int size() {
		return array.size();
	}

	public void clear() {
		array.clear();
	}

	public SimpleFrameCache setHead(byte[] head) {
		this.head = head;
		return this;
	}

	public SimpleFrameCache setTail(byte[] tail) {
		this.tail = tail;
		return this;
	}

	public byte[] getHead() {
		return head;
	}

	public byte[] getTail() {
		return tail;
	}

}
