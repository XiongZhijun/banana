/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol.command;

import org.springframework.beans.factory.annotation.Autowired;

import os.banana.protocol.Matcher;
import os.banana.protocol.Server;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class MeasureDataController implements CommandController<Command>,
		Matcher<Command> {
	@Autowired(required = false)
	private MeasureDataSaver measureDataSaver;
	@Autowired(required = false)
	private MeasureDataNotifier measureDataNotifier;

	public void handle(Command command, CommandSender sender, Server server) {
		MeasureData measureData = command.getMeasureData();
		if (measureDataSaver != null) {
			measureDataSaver.save(measureData);
		}
		if (measureDataNotifier != null) {
			measureDataNotifier.notify(measureData);
		}
	}

	public boolean match(Command target) {
		return target != null && target.isMeasureData()
				&& target.getMeasureData() != null;
	}

	public void setMeasureDataSaver(MeasureDataSaver measureDataSaver) {
		this.measureDataSaver = measureDataSaver;
	}

	public void setMeasureDataNotifier(MeasureDataNotifier measureDataNotifier) {
		this.measureDataNotifier = measureDataNotifier;
	}

	public static interface MeasureDataSaver {
		void save(MeasureData measureData);
	}

	public static interface MeasureDataNotifier {
		void notify(MeasureData measureData);
	}
}
