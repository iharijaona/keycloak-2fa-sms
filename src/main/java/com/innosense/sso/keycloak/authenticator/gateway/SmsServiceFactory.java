package com.innosense.sso.keycloak.authenticator.gateway;

import org.jboss.logging.Logger;

import java.util.Map;

/**
 * @author Harijaona Ravelondrina <hravelondrina@gmail.com>
 */
public class SmsServiceFactory {

	private static final Logger LOG = Logger.getLogger(SmsServiceFactory.class);

	public static ISmsService get(Map<String, String> config) {
		if (Boolean.parseBoolean(config.getOrDefault("simulation", "false"))) {
			return (phoneNumber, message) -> LOG.warn(String
					.format("***** SIMULATION MODE ***** Would send SMS to %s with text: %s", phoneNumber, message));
		} else {
			return new AwsSmsService(config);
		}
	}

}
