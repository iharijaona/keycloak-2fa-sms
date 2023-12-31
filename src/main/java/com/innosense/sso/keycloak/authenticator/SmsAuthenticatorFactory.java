package com.innosense.sso.keycloak.authenticator;

import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.Arrays;
import java.util.List;

/**
 * @author Harijaona Ravelondrina <hravelondrina@gmail.com>
 * 
 */
public class SmsAuthenticatorFactory implements AuthenticatorFactory {

	@Override
	public String getId() {
		return "sms-otp-authenticator";
	}

	@Override
	public String getDisplayType() {
		return "SMS OTP Authentication";
	}

	@Override
	public String getHelpText() {
		return "Validates an OTP sent via SMS to the users mobile phone.";
	}

	@Override
	public String getReferenceCategory() {
		return "otp";
	}

	@Override
	public boolean isConfigurable() {
		return true;
	}

	@Override
	public boolean isUserSetupAllowed() {
		return true;
	}

	@Override
	public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
		return new AuthenticationExecutionModel.Requirement[] {
				AuthenticationExecutionModel.Requirement.REQUIRED,
				AuthenticationExecutionModel.Requirement.ALTERNATIVE,
				AuthenticationExecutionModel.Requirement.DISABLED,
		};
	}

	@Override
	public List<ProviderConfigProperty> getConfigProperties() {
		return Arrays.asList(
				new ProviderConfigProperty("length", "Code length", "The number of digits of the generated code.",
						ProviderConfigProperty.STRING_TYPE, 6),
				new ProviderConfigProperty("ttl", "Time-to-live",
						"The time to live in seconds for the code to be valid.", ProviderConfigProperty.STRING_TYPE,
						"300"),
				new ProviderConfigProperty("senderId", "SenderId",
						"The sender ID is displayed as the message sender on the receiving device.",
						ProviderConfigProperty.STRING_TYPE, "Keycloak"),
				new ProviderConfigProperty("simulation", "Simulation mode",
						"In simulation mode, the SMS won't be sent, but printed to the server logs",
						ProviderConfigProperty.BOOLEAN_TYPE, true),

				new ProviderConfigProperty("awsAccessKeyId", "AWS access key",
						"The AWS access key, used to identify the user interacting with AWS.",
						ProviderConfigProperty.STRING_TYPE, ""),
				new ProviderConfigProperty("awsSecretAccessKey", "AWS secret access",
						"The number of digits of the generated code.",
						ProviderConfigProperty.STRING_TYPE,
						""),
				new ProviderConfigProperty("awsRegion", "The region",
						"The AWS secret access key, used to authenticate the user interacting with AWS.",
						ProviderConfigProperty.STRING_TYPE, ""));
	}

	@Override
	public Authenticator create(KeycloakSession session) {
		return new SmsAuthenticator();
	}

	@Override
	public void init(Config.Scope config) {
	}

	@Override
	public void postInit(KeycloakSessionFactory factory) {
	}

	@Override
	public void close() {
	}

}
