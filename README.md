# Keycloak 2FA SMS Authenticator

Keycloak Authentication Provider implementation to get a 2nd-factor authentication with a OTP/code/token send via SMS (through AWS SNS).

## Build

```
$ docker run -it --rm --name build-keycloak-2fa-sms -v "$(pwd)/.m2":/root/.m2 -v "$(pwd)":/usr/src/keycloak-2fa-sms -w /usr/src/keycloak-2fa-sms maven:3.8-jdk-11 mvn clean install

```
