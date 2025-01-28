package com.bansikah.keycloakdemo.service;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class KeycloakService {

    private KeycloakConnexion keycloakConnexion;

    public KeycloakService() {
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl(SERVER_URL)
                .realm(REALM_NAME)
                .clientId(CLIENT_ID)
                .username(USERNAME)
                .password(PASSWORD)
                .build();
    }

    public void addRoleToUser(String userId, String roleName) {
        RoleRepresentation role = keycloak.realm("food-ordering-realm")
                .roles()
                .get(roleName)
                .toRepresentation();

        keycloak.realm("food-ordering-realm")
                .users()
                .get(userId)
                .roles()
                .clientLevel("food-ordering-client")
                .add(Arrays.asList(role));
    }

    public void removeRoleFromUser(String userId, String roleName) {
        RoleRepresentation role = keycloak.realm("food-ordering-realm")
                .roles()
                .get(roleName)
                .toRepresentation();

        keycloak.realm("food-ordering-realm")
                .users()
                .get(userId)
                .roles()
                .clientLevel("food-ordering-client")
                .remove(Arrays.asList(role));
    }

    public boolean hasUserRole(String userId, String roleName) {
        List<RoleRepresentation> roles = keycloak.realm("food-ordering-realm")
                .users()
                .get(userId)
                .roles()
                .clientLevel("food-ordering-client")
                .listAll();

        return roles.stream().anyMatch(role -> role.getName().equals(roleName));
    }
}

