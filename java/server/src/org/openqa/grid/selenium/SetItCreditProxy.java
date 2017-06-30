package org.openqa.grid.selenium;

/*import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.openqa.grid.common.RegistrationRequest;
import org.openqa.grid.common.exception.RemoteException;
import org.openqa.grid.internal.Registry;
import org.openqa.grid.internal.TestSession;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.groupon.seleniumgridextras.grid.proxies.SetupTeardownProxy;
import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.ServiceCheck;
import com.timgroup.statsd.StatsDClient;*/

public class SetItCreditProxy /*extends SetupTeardownProxy */{

/*    private static Logger logger = Logger.getLogger(SetItCreditProxy.class);

	private final StatsDClient statsd;

	public SetItCreditProxy(RegistrationRequest request, Registry registry) {

		super(request, registry);

		try {

			Method getConfigurationMethod = RegistrationRequest.class.getMethod("getConfiguration", new Class<?> [0]);
			Object configuration = (Object) getConfigurationMethod.invoke(request, new Object [0]);

			Field capabilitiesField = configuration.getClass().getField("capabilities");
			List<?> capabilitiesList = (List<?>) capabilitiesField.get(configuration);
			Map<String, Object> capabilities = new HashMap<String, Object> (((Map<String, ?>) ((DesiredCapabilities) capabilitiesList.get(0)).asMap()));
			capabilities.remove("password");

			String capabilitiesString = "" + capabilities;
			logger.info("Status update: " + GridStatusUpdate.STATUS_READY + " " + capabilitiesString);
			GridStatusUpdate.update(GridStatusUpdate.STATUS_READY, capabilitiesString);
		} catch (Throwable ex) {

			System.err.println(ex.getMessage());
			ex.printStackTrace(System.err);
			logger.error("Unable to send status update: " + ex.getLocalizedMessage(), ex);
			throw new RuntimeException("Unable to send status update: " + ex.getMessage(), ex);
		}

		String[] tags = new String[] { "registry-hub:" + registry.getHub().getUrl() };
		this.statsd = new NonBlockingStatsDClient(
				this.getClass().getName(),
				"localhost",
				8125,
				tags);

		logger.info("SetItCreditProxy:" + Arrays.asList(tags));
	}

	@Override
	public void beforeSession(TestSession session) {

		logger.info("beforeSession(" + session + ")");

		Date startTime = new Date();
		session.put(this.getClass().getCanonicalName() + "sessionstarttime", startTime);

		super.beforeSession(session);
	}

	@Override
	public void afterSession(TestSession session) {

		logger.info("afterSession(" + session + ")");

		Date startTime = (Date) session.get(this.getClass().getCanonicalName() + "sessionstarttime");
		Date endTime = new Date();

		String[] tags = new String[] { "session:" + session.getInternalKey(), "jobuuid:" + session.getRequestedCapabilities().get("jobuuid") };
		statsd.recordExecutionTime("session", endTime.getTime() - startTime.getTime(), tags);

		ServiceCheck sc = ServiceCheck
				.builder()
				.withName("com.setitcredit.SetItCreditProxy")
				.withStatus(ServiceCheck.Status.OK)
				.withTags(tags)
				.build();
		statsd.serviceCheck(sc);

		super.afterSession(session);
	}

	@Override
	public void beforeCommand(TestSession session, HttpServletRequest request, HttpServletResponse response) {

		logger.info("beforeCommand(" + session + ")");

		Date startTime = new Date();
		session.put(this.getClass().getCanonicalName() + "commandstarttime", startTime);

		super.beforeCommand(session, request, response);
	}

	@Override
	public void afterCommand(TestSession session, HttpServletRequest request, HttpServletResponse response) {

		logger.info("afterCommand(" + session + ")");

		Date startTime = (Date) session.get(this.getClass().getCanonicalName() + "commandstarttime");
		Date endTime = new Date();

		String[] tags = new String[] { "command:" + request.getMethod() + "-" + request.getPathInfo(), "jobuuid:" + session.getRequestedCapabilities().get("jobuuid") };
		statsd.recordExecutionTime("command", endTime.getTime() - startTime.getTime(), tags);

		ServiceCheck sc = ServiceCheck
				.builder()
				.withName("com.setitcredit.SetItCreditProxy")
				.withStatus(ServiceCheck.Status.OK)
				.withTags(tags)
				.build();
		statsd.serviceCheck(sc);

		super.afterCommand(session, request, response);
	}

	@Override
	public void onEvent(List<RemoteException> events, RemoteException lastInserted) {

		logger.info("onEvent(" + events + ")");

		String[] tags = new String[] { "exception:" + lastInserted.getClass().getSimpleName(), "message:" + lastInserted.getMessage() };

		ServiceCheck sc = ServiceCheck
				.builder()
				.withName("com.setitcredit.SetItCreditProxy")
				.withStatus(ServiceCheck.Status.CRITICAL)
				.withTags(tags)
				.build();
		statsd.serviceCheck(sc);

		super.onEvent(events, lastInserted);
	}





*/
	/*		statsd.incrementCounter("foo");
	statsd.recordGaugeValue("bar", 100);
	statsd.recordGaugeValue("baz", 0.01);
	statsd.recordHistogramValue("qux", 15);
	statsd.recordHistogramValue("qux", 15.5);

	ServiceCheck sc = ServiceCheck
			.builder()
			.withName("my.check.name")
			.withStatus(ServiceCheck.Status.OK)
			.build();
	statsd.serviceCheck(sc);

	statsd.recordExecutionTime("bag", 25, "cluster:foo");*/

}
