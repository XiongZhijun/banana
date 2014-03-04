/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com 
 */
package os.banana.db.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import os.banana.protocol.command.MeasureData;
import os.banana.protocol.command.MeasureDataController.MeasureDataSaver;

/**
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class SimpleMeasureDataSaver implements MeasureDataSaver {

	@Autowired
	private MongoTemplate mongoTemplate;

	public void save(MeasureData measureData) {
		mongoTemplate.save(measureData, MeasureData.TABLE_NAME);
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
