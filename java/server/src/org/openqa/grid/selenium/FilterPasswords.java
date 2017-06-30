package org.openqa.grid.selenium;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterPasswords implements LogFilter {

	private static final Pattern PATTERN = Pattern.compile(
			"^(.*password=)" +
			"([^,]+)" +
			"(, .*)$");

	@Override
	public String filter(String text) {

		Matcher matcher = PATTERN.matcher(text);
		if (! matcher.matches()) return text;

		return matcher.group(1) + "**OMITTED: PASSWORD**" + matcher.group(3);
	}
}
