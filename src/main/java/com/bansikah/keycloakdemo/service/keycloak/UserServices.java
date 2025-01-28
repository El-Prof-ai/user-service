package com.bansikah.keycloakdemo.service.keycloak;

import lombok.AllArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserServices {
    private Keycloak keycloak;

    public String createUser(String username, String email, String firstName, String lastName, String password, String realm) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEnabled(true);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);
        user.setCredentials(Collections.singletonList(credential));

        Response response = keycloak.realm(realm).users().create(user);

        if (response.getStatus() == 201) {
            String locationHeader = response.getHeaderString("Location");
            String userId = locationHeader.substring(locationHeader.lastIndexOf("/") + 1);
            response.close();
            return userId;
        } else {
            response.close();
            throw new RuntimeException("Failed to create user. Status: " + response.getStatus());
        }
    }

    // READ: Méthode pour récupérer un utilisateur par ID
    public Map<String, Object> getUser(String userId, String realm) {
        UserRepresentation user = keycloak.realm(realm).users().get(userId).toRepresentation();
        return Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "email", user.getEmail(),
                "firstName", user.getFirstName(),
                "lastName", user.getLastName(),
                "enabled", user.isEnabled()
        );
    }

    // READ: Méthode pour récupérer tous les utilisateurs
    /*public List<Map<String, Serializable>> getAllUsers(String realm) {
        return keycloak.realm(realm).users().list().stream()
                .map(user -> Map.of(
                        "id", user.getId(),
                        "username", user.getUsername(),
                        "email", user.getEmail(),
                        "firstName", user.getFirstName(),
                        "lastName", user.getLastName(),
                        "enabled", user.isEnabled()
                ))
                .collect(Collectors.toList());
    }*/

    public void updateUser(String userId, String email, String firstName, String lastName, String realm) {
        UserRepresentation user = keycloak.realm(realm).users().get(userId).toRepresentation();
        if (email != null) user.setEmail(email);
        if (firstName != null) user.setFirstName(firstName);
        if (lastName != null) user.setLastName(lastName);

        keycloak.realm(realm).users().get(userId).update(user);
    }
    public void deleteUser(String userId, String realm) {
        keycloak.realm(realm).users().get(userId).remove();
    }
}
