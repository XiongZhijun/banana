/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.banana.hbgb;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import os.banana.protocol.CommandDecoder;
import os.banana.protocol.CommandEncoder;
import os.banana.protocol.command.Command;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@Component
public class HBGBCommandParser implements CommandEncoder, CommandDecoder {
	private static final String MN = "MN";
	private static final String CN = "CN";
	private static final Pattern COMMAND_PATTERN = Pattern
			.compile("^((\\w+=[^=;]+;)+)CP=&&.*&&$");
	private static final Pattern RECORD_PATTERN = Pattern
			.compile("^(\\w+)=([^=;]+)");

	public Command decode(byte[] bytes) {
		Matcher matcher = COMMAND_PATTERN.matcher(new String(bytes));
		if (!matcher.matches()) {
			return null;
		}
		Map<String, String> map = toMap(matcher.group(1));
		HBGBCommand command = new HBGBCommand();
		command.setId(map.get(CN));
		command.setTerminalCode(map.get(MN));
		return command;
	}

	public byte[] encode(Command command) {
		StringBuilder sb = new StringBuilder();
		sb.append(CN).append("=").append(command.getId()).append(";");
		sb.append(MN).append("=").append(command.getTerminalCode()).append(";");
		sb.append("CP=&&&&");
		return sb.toString().getBytes();
	}

	protected Map<String, String> toMap(String input) {
		Map<String, String> map = new HashMap<String, String>();
		String[] records = input.split(";");
		for (String record : records) {
			Matcher matcher = RECORD_PATTERN.matcher(record);
			if (matcher.matches()) {
				map.put(matcher.group(1), matcher.group(2));
			}
		}
		return map;
	}
}
