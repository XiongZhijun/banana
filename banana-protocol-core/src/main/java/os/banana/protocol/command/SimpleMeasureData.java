/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com 
 */
package os.banana.protocol.command;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class SimpleMeasureData implements MeasureData {
	private String terminalCode;
	private Date date;
	private Map<String, Object> fieldValues = new HashMap<String, Object>();

	public String getTerminalCode() {
		return terminalCode;
	}

	public Date getDate() {
		return date;
	}

	public Object getFieldValue(String field) {
		return fieldValues.get(field);
	}

	public SimpleMeasureData putFieldValue(String field, Object value) {
		fieldValues.put(field, value);
		return this;
	}

	public Map<String, Object> getFieldValues() {
		return fieldValues;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setFieldValues(Map<String, Object> fieldValues) {
		this.fieldValues.clear();
		if (fieldValues != null) {
			this.fieldValues.putAll(fieldValues);
		}
	}

}
