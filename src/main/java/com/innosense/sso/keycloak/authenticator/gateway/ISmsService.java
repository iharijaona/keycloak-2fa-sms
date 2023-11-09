package com.innosense.sso.keycloak.authenticator.gateway;

/**
 * @author Harijaona Ravelondrina <hravelondrina@gmail.com>,
 * 
 */
public interface ISmsService {

	void send(String phoneNumber, String message);

}
