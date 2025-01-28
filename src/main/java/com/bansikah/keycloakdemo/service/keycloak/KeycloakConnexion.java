package com.bansikah.keycloakdemo.service.keycloak;

import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.Keycloak;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class KeycloakConnexion {
    private final String KEYCLOAK_SERVER_URL = "http://localhost:8088";
    public static final String DEFAULT_REALM_NAME = "synthese_realm";
    private final String CLIENT_ID = "admin-synthese-client";
    private final String USERNAME = "admin2";
    private final String PASSWORD = "admin2";
    private Keycloak keycloak;

    public KeycloakConnexion() {
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl(KEYCLOAK_SERVER_URL)
                .realm(DEFAULT_REALM_NAME)
                .clientId(CLIENT_ID)
                .username(USERNAME)
                .password(PASSWORD)
                .clientSecret("J9WA3qLaradpDCqxFKsk8wek9ARoQzbS")
                .build();
    }

    @Bean
    public Keycloak getKeycloak() {
        return this.keycloak;
    }
}
