/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol.command;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class SimpleCommand implements Command {

	private String id;
	private Object content;

	public SimpleCommand() {
		this(null);
	}

	public SimpleCommand(String id) {
		this(id, null);
	}

	public SimpleCommand(String id, Object content) {
		this.id = id;
		this.content = content;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public Object getContent() {
		return this.content;
	}

	public String buildSendSerialNumber() {
		return this.id;
	}

	public String getSendedSerialNumber() {
		return this.id;
	}

}
