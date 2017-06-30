package org.openqa.grid.selenium;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterEmailAddresses implements LogFilter {

	public static final Pattern PATTERN = Pattern.compile(
			"^(.*[^A-Z0-9._%+-])([A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6})([^A-Z].*)$", 
			Pattern.CASE_INSENSITIVE);

	@Override
	public String filter(String text) {

		if (text.endsWith("\n")) text = text.substring(0, text.length()-1);

		Matcher matcher = PATTERN.matcher(text);
		if (! matcher.matches()) return text;

		return matcher.group(1) + "**OMITTED: EMAIL**" + matcher.group(3);
	}
}
