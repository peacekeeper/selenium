package org.openqa.grid.selenium;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class PrivacyFormatter extends Formatter {
	private Formatter baseFormatter;
	public PrivacyFormatter(Formatter baseFormatter) {
		this.baseFormatter = baseFormatter;
	}
	@Override
	public String format(LogRecord record) {
		String result = this.baseFormatter.format(record);
		if (result.endsWith("\n")) result = result.substring(0, result.length()-1);
		for (LogFilter filter : LogFilter.filters) result = filter.filter(result);
		return result + "\n";
	}
}
