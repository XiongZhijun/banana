/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com 
 */
package os.banana.protocol.command;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class SimpleMeasureDataTest {
	private SimpleMeasureData measureData;
	private Date now;

	@Before
	public void setUp() {
		measureData = new SimpleMeasureData();
		measureData.setTerminalCode("Terminal_000011");
		now = new Date();
		measureData.setDate(now);
		measureData.putFieldValue("S01", 100);
		measureData.putFieldValue("S02", 130);
	}

	@Test
	public void testGetTerminalCode() {
		assertEquals("Terminal_000011", measureData.getTerminalCode());
	}

	@Test
	public void testGetDate() {
		assertEquals(now, measureData.getDate());
	}

	@Test
	public void testGetFieldValue() {
		assertEquals(100, measureData.getFieldValue("S01"));
		assertEquals(130, measureData.getFieldValue("S02"));
	}

	@Test
	public void testGetFieldValues() {
		assertEquals(2, measureData.getFieldValues().size());
		assertEquals(100, measureData.getFieldValues().get("S01"));
		assertEquals(130, measureData.getFieldValues().get("S02"));

		measureData.setFieldValues(null);
		assertEquals(0, measureData.getFieldValues().size());
		Map<String, Object> fieldValues = new HashMap<String, Object>();
		fieldValues.put("S33", "OK");
		fieldValues.put("S03", 100.0);
		fieldValues.put("D03", new Date());
		measureData.setFieldValues(fieldValues);
		assertEquals(3, measureData.getFieldValues().size());

		Map<String, Object> fieldValues2 = new HashMap<>();
		fieldValues2.put("DDD", "F");
		measureData.setFieldValues(fieldValues2);
		assertEquals(1, measureData.getFieldValues().size());

		measureData.setFieldValues(new HashMap<String, Object>());
		assertEquals(0, measureData.getFieldValues().size());
	}

}
