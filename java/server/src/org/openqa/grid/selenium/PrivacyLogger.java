package org.openqa.grid.selenium;

import java.util.Enumeration;

public class PrivacyLogger {

	private static java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PrivacyLogger.class.getName());

	private static void log(String string) {

		logger.info(string);
	}

	public static void configurePrivacyLogger() {

		log("Configuring loggers");

		// java logging

		for (Enumeration<String> nn = java.util.logging.LogManager.getLogManager().getLoggerNames(); nn.hasMoreElements(); ) {
			String n = nn.nextElement();
			java.util.logging.Logger l = java.util.logging.LogManager.getLogManager().getLogger(n);
			log("Configuring privacy logger: " + l.getName());
			for (java.util.logging.Handler h : l.getHandlers()) {
				if (h.getFormatter() instanceof PrivacyFormatter) continue;
				h.setFormatter(new PrivacyFormatter(h.getFormatter()));
			}
		}
	}
}
