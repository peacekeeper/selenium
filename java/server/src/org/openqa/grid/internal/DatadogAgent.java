package org.openqa.grid.selenium;

import java.util.Arrays;

import org.openqa.grid.internal.GridStatusUpdate;
import org.openqa.grid.internal.Registry;

import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.ServiceCheck;
import com.timgroup.statsd.StatsDClient;

public class DatadogAgent {

	private static java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DatadogAgent.class.getName());

	public static StatsDClient statsd = null;

	public static StatsDClient get(Registry registry) {

		if (statsd != null) return statsd;
		
		String[] tags = new String[] { "registry-hub:" + registry.getHub().getUrl() };
		statsd = new NonBlockingStatsDClient(
				DatadogAgent.class.getName(),
				"localhost",
				8125,
				tags);

		logger.info("DatadogAgent: " + statsd + ": " + Arrays.asList(tags));

		return statsd;
	}

	public static ServiceCheck.Builder serviceCheckBuilder() {
		
		return ServiceCheck
				.builder()
				.withName("grid-" + GridStatusUpdate.envGridUuid());
	}
}
