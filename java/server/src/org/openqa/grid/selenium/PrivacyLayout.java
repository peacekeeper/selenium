package org.openqa.grid.selenium;

/*import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;*/

public class PrivacyLayout /*extends Layout */{
/*	private Layout baseLayout;
	public PrivacyLayout(Layout baseLayout) {
		this.baseLayout = baseLayout;
	}
	public void activateOptions() {
		baseLayout.activateOptions();
	}
	public String format(LoggingEvent event) {
		String result = this.baseLayout.format(event);
		if (result.endsWith("\n")) result = result.substring(0, result.length()-1);
		for (LogFilter filter : LogFilter.filters) result = filter.filter(result);
		return result + "\n";
	}
	public boolean ignoresThrowable() {
		return baseLayout.ignoresThrowable();
	}*/
}