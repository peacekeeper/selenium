package org.openqa.grid.selenium;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterCreditCardNumbers implements LogFilter {

	private static final Pattern PATTERN = Pattern.compile(
			"^(.*\\D)(4[0-9]{12}(?:[0-9]{3})?" +
					"|(?:5[1-5][0-9]{2}" +
					"|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}" +
					"|3[47][0-9]{13}" +
					"|3(?:0[0-5]|[68][0-9])[0-9]{11}" +
					"|6(?:011|5[0-9]{2})[0-9]{12}" +
					"|(?:2131|1800|35\\d{3})\\d{11}" +
			")(\\D.*)$");

	@Override
	public String filter(String text) {

		Matcher matcher = PATTERN.matcher(text);
		if (! matcher.matches()) return text;

		return matcher.group(1) + "**OMITTED: CREDIT CARD**" + matcher.group(3);
	}
}
