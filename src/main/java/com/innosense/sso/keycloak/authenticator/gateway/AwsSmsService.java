package com.innosense.sso.keycloak.authenticator.gateway;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.MessageAttributeValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Harijaona Ravelondrina <hravelondrina@gmail.com>
 */
public class AwsSmsService implements ISmsService {

	private final SnsClient snsClient;

	private final String senderId;

	/**
	 * Init the config
	 */
	AwsSmsService(Map<String, String> config) {
		senderId = config.get("senderId");
		snsClient = SnsClient.builder()
				.credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(
						config.get("awsAccessKeyId"),
						config.get("awsSecretAccessKey"))))
				.region(Region.of(config.get(
						"awsRegion")))
				.build();
	}

	/**
	 * Send the OTP code
	 */
	@Override
	public void send(String phoneNumber, String message) {
		Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
		messageAttributes.put("AWS.SNS.SMS.SenderID",
				MessageAttributeValue.builder().stringValue(senderId).dataType("String").build());
		messageAttributes.put("AWS.SNS.SMS.SMSType",
				MessageAttributeValue.builder().stringValue("Transactional").dataType("String").build());

		snsClient.publish(builder -> builder
				.message(message)
				.phoneNumber(phoneNumber)
				.messageAttributes(messageAttributes));
	}

}
