package org.openqa.grid.selenium;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openqa.grid.common.RegistrationRequest;
import org.openqa.grid.common.exception.RemoteException;
import org.openqa.grid.internal.Registry;
import org.openqa.grid.internal.TestSession;
import org.openqa.grid.selenium.proxy.DefaultRemoteProxy;

import com.timgroup.statsd.ServiceCheck;
import com.timgroup.statsd.StatsDClient;

public class SetItCreditProxy extends DefaultRemoteProxy {

	private static java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SetItCreditProxy.class.getName());

	private StatsDClient statsd;
	
	public SetItCreditProxy(RegistrationRequest request, Registry registry) {

		super(request, registry);

		statsd = DatadogAgent.get(registry);
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

		ServiceCheck sc = DatadogAgent.serviceCheckBuilder()
				.withStatus(ServiceCheck.Status.OK)
				.withTags(tags)
				.build();
		statsd.serviceCheck(sc);
		logger.info("Service check: " + sc.getMessage());

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

		ServiceCheck sc = DatadogAgent.serviceCheckBuilder()
				.withStatus(ServiceCheck.Status.OK)
				.withTags(tags)
				.build();
		statsd.serviceCheck(sc);
		logger.info("Service check: " + sc.getMessage());

		super.afterCommand(session, request, response);
	}

	@Override
	public void onEvent(List<RemoteException> events, RemoteException lastInserted) {

		logger.info("onEvent(" + events + ")");

		String[] tags = new String[] { "exception:" + lastInserted.getClass().getSimpleName(), "message:" + lastInserted.getMessage() };

		ServiceCheck sc = DatadogAgent.serviceCheckBuilder()
				.withStatus(ServiceCheck.Status.CRITICAL)
				.withTags(tags)
				.build();
		statsd.serviceCheck(sc);
		logger.info("Service check: " + sc.getMessage());

		super.onEvent(events, lastInserted);
	}






	/*		statsd.incrementCounter("foo");
	statsd.recordGaugeValue("bar", 100);
	statsd.recordGaugeValue("baz", 0.01);
	statsd.recordHistogramValue("qux", 15);
	statsd.recordHistogramValue("qux", 15.5);

		ServiceCheck sc = DatadogAgent.serviceCheckBuilder()
			.withStatus(ServiceCheck.Status.OK)
			.build();
	statsd.serviceCheck(sc);
	logger.info("Service check: " + sc.getMessage());

	statsd.recordExecutionTime("bag", 25, "cluster:foo");*/

}
