/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com 
 */
package os.banana.db.mongodb;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import os.banana.protocol.command.MeasureData;

/**
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@ContextConfiguration
public class SimpleMeasureDataSaverTest extends
		AbstractJUnit4SpringContextTests {
	@Autowired
	private SimpleMeasureDataSaver saver;
	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	public void testSave() {
		mongoTemplate.dropCollection(MeasureData.TABLE_NAME);
		TestMeasureData measureData = new TestMeasureData();
		measureData.setTerminalCode("Terminal_001");
		measureData.setDate(new Date());
		measureData.putFieldValue("S02", 100.1d);
		measureData.putFieldValue("S01", 90.1d);
		measureData.putFieldValue("S04", 190.1d);
		saver.save(measureData);
		List<TestMeasureData> datas = mongoTemplate.findAll(
				TestMeasureData.class, MeasureData.TABLE_NAME);
		assertEquals(1, datas.size());
		assertEquals("Terminal_001", datas.get(0).getTerminalCode());
		assertEquals(90.1d, datas.get(0).getFieldValue("S01"));
	}

	public static class TestMeasureData implements MeasureData {

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

		public TestMeasureData putFieldValue(String field, Object value) {
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
			fieldValues.clear();
			if (fieldValues != null) {
				this.fieldValues.putAll(fieldValues);
			}
		}

	}

	public static class User {
		private String name;
		private String password;
		private int age;

		public User(String name, String password, int age) {
			super();
			this.name = name;
			this.password = password;
			this.age = age;
		}

		public User() {
			super();
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	}
}
