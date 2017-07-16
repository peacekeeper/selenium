package org.openqa.grid.selenium;

public class FilterPasswords implements LogFilter {

	/*	private static final Pattern PATTERN = Pattern.compile(
			"^(.*password=)" +
			"([^,]+)" +
			"(, .*)$");

	@Override
	public String filter(String text) {

		Matcher matcher = PATTERN.matcher(text);
		if (! matcher.matches()) return text;

		return matcher.group(1) + "**OMITTED: PASSWORD**" + matcher.group(3);
	}*/

	@Override
	public String filter(String text) {

		if (text.toLowerCase().contains("password") &&
				(! text.toLowerCase().contains("jobuuid")) &&
				(! text.toLowerCase().contains("capabilities")))
			return "**OMITTED: PASSWORD**";

		return text;
	}
}
