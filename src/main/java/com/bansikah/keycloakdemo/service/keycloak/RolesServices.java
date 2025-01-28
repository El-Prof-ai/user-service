package com.bansikah.keycloakdemo.service.keycloak;

import lombok.AllArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RolesServices {

    private static final String DEFAULT_REALM_NAME = KeycloakConnexion.DEFAULT_REALM_NAME;
    private Keycloak keycloak;
    public void createRole(String roleName) {
        RoleRepresentation role = new RoleRepresentation();
        role.setName(roleName);
        keycloak.realm(DEFAULT_REALM_NAME).roles().create(role);
    }
    public List<String> getAllRoles() {
        return keycloak.realm(DEFAULT_REALM_NAME)
                .roles()
                .list()
                .stream()
                .map(RoleRepresentation::getName)
                .collect(Collectors.toList());
    }
    public RoleRepresentation getRole(String roleName) {
        return keycloak.realm(DEFAULT_REALM_NAME)
                .roles()
                .get(roleName)
                .toRepresentation();
    }
    public void updateRole(String roleName, String newDescription) {
        RoleRepresentation role = keycloak.realm(DEFAULT_REALM_NAME)
                .roles()
                .get(roleName)
                .toRepresentation();

        role.setDescription(newDescription); // Update the description
        keycloak.realm(DEFAULT_REALM_NAME)
                .roles()
                .get(roleName)
                .update(role);
    }
    public void deleteRole(String roleName) {
        keycloak.realm(DEFAULT_REALM_NAME)
                .roles()
                .get(roleName)
                .remove();
    }
}
