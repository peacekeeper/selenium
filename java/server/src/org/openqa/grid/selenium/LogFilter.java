package org.openqa.grid.selenium;

public interface LogFilter {

	public static LogFilter[] filters = new LogFilter[] {
			new FilterCreditCardNumbers(),
			new FilterEmailAddresses(),
			new FilterPasswords()
	};

	public String filter(String text);
}
