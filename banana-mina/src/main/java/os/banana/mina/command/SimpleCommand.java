/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.mina.command;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class SimpleCommand implements Command {

	private String id;
	private Object content;

	public SimpleCommand() {
	}

	public SimpleCommand(String id) {
		this.id = id;
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

}
