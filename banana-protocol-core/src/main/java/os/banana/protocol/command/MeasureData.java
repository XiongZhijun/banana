/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol.command;

import java.util.Date;
import java.util.Map;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public interface MeasureData {

	String TABLE_NAME = MeasureData.class.getSimpleName();

	String getTerminalCode();

	Date getDate();

	Object getFieldValue(String field);

	Map<String, Object> getFieldValues();
}
