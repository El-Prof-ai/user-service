package com.bansikah.keycloakdemo.service.keycloak;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleMappingServices {
    private final String DEFAULT_REALM_NAME = KeycloakConnexion.DEFAULT_REALM_NAME;
    private final Keycloak keycloak;
    public void addRoleToUser(String userId, String roleName) {
        RoleRepresentation role = keycloak.realm(DEFAULT_REALM_NAME)
                .roles()
                .get(roleName)
                .toRepresentation();

        keycloak.realm(DEFAULT_REALM_NAME)
                .users()
                .get(userId)
                .roles()
                .clientLevel(DEFAULT_REALM_NAME)
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
                .clientLevel(DEFAULT_REALM_NAME)
                .remove(Arrays.asList(role));
    }
    public boolean hasUserRole(String userId, String roleName) {
        List<RoleRepresentation> roles = keycloak.realm("food-ordering-realm")
                .users()
                .get(userId)
                .roles()
                .clientLevel(DEFAULT_REALM_NAME)
                .listAll();

        return roles.stream().anyMatch(role -> role.getName().equals(roleName));
    }
}

