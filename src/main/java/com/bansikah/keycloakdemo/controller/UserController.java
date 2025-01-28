package com.bansikah.keycloakdemo.controller;

import com.bansikah.keycloakdemo.service.keycloak.KeycloakConnexion;
import com.bansikah.keycloakdemo.service.keycloak.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final String DEFAULT_REALM_NAME = KeycloakConnexion.DEFAULT_REALM_NAME;
    private final UserServices userServices;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestParam String username, @RequestParam String email,
                                             @RequestParam String firstName, @RequestParam String lastName,
                                             @RequestParam String password) {
        try {
            String userId = userServices.createUser(username, email, firstName, lastName, password, DEFAULT_REALM_NAME);
            return ResponseEntity.ok("User created successfully. User ID: " + userId);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating user: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable String userId) {
        try {
            Map<String, Object> user = userServices.getUser(userId, DEFAULT_REALM_NAME);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestParam String userId, @RequestParam String email,
                                             @RequestParam String firstName, @RequestParam String lastName) {
        try {
            userServices.updateUser(userId, email, firstName, lastName, DEFAULT_REALM_NAME);
            return ResponseEntity.ok("User updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating user: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam String userId) {
        try {
            userServices.deleteUser(userId, DEFAULT_REALM_NAME);
            return ResponseEntity.ok("User deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting user: " + e.getMessage());
        }
    }
}
