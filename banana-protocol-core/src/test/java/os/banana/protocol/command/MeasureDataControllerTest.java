/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.protocol.command;

import static org.junit.Assert.*;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import os.banana.protocol.command.MeasureDataController.MeasureDataNotifier;
import os.banana.protocol.command.MeasureDataController.MeasureDataSaver;
import static org.easymock.EasyMock.*;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@RunWith(EasyMockRunner.class)
public class MeasureDataControllerTest {
	@TestSubject
	private MeasureDataController controller = new MeasureDataController();
	@Mock
	private MeasureDataSaver saver;
	@Mock
	private MeasureDataNotifier notifier;
	@Mock
	private Command command;
	@Mock
	private Command measureDataCommand;
	@Mock
	private MeasureData measureData;

	@Test
	public void testHandle() {
		expect(measureDataCommand.getMeasureData()).andReturn(measureData);
		saver.save(measureData);
		notifier.notify(measureData);

		replay(measureDataCommand, saver, notifier);

		controller.handle(measureDataCommand, null, null);

		verify(measureDataCommand, saver, notifier);
	}

	@Test
	public void testMatch() {
		expect(command.isMeasureData()).andReturn(false);
		expect(measureDataCommand.isMeasureData()).andReturn(true);
		expect(measureDataCommand.getMeasureData()).andReturn(measureData);
		replay(command, measureDataCommand);
		assertFalse(controller.match(null));
		assertFalse(controller.match(command));
		assertTrue(controller.match(measureDataCommand));
		verify(command, measureDataCommand);
	}

}
