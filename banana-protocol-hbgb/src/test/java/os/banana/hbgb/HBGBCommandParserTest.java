/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.hbgb;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class HBGBCommandParserTest {
	private HBGBCommandParser parser = new HBGBCommandParser();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDecode() {
		HBGBCommand command = (HBGBCommand) parser
				.decode("QN=20140227;CN=2011;MN=Terminal_1011;CP=&&&&"
						.getBytes());
		assertEquals("2011", command.getId());
		assertEquals("Terminal_1011", command.getTerminalCode());
	}

	@Test
	public void testEncode() {
		HBGBCommand command = new HBGBCommand();
		command.setId("2011");
		command.setTerminalCode("Terminal_1011");
		assertEquals("CN=2011;MN=Terminal_1011;CP=&&&&",
				new String(parser.encode(command)));
	}

}
