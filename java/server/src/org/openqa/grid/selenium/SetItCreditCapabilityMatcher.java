package org.openqa.grid.selenium;

import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.openqa.grid.internal.utils.DefaultCapabilityMatcher;

public class SetItCreditCapabilityMatcher extends DefaultCapabilityMatcher {

	private static final Logger log = Logger.getLogger(SetItCreditCapabilityMatcher.class.getName());

	public SetItCreditCapabilityMatcher() {

		super();
		toConsider.add("password");
	}

	@Override
	public boolean matches(Map<String, Object> nodeCapability, Map<String, Object> requestedCapability) {

		if (! requestedCapability.containsKey("jobuuid")) return false;
		if (UUID.fromString((String) requestedCapability.get("jobuuid")) == null) return false;

		if (! nodeCapability.containsKey("password")) return false;
		if (! requestedCapability.containsKey("password")) return false;

		return super.matches(nodeCapability, requestedCapability);
	}
}
