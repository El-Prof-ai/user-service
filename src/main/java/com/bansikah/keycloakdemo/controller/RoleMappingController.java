package com.bansikah.keycloakdemo.controller;

import com.bansikah.keycloakdemo.service.keycloak.RoleMappingServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@AllArgsConstructor
public class RoleMappingController {

    private final RoleMappingServices roleMappingServices;
    @PostMapping("/add")
    public ResponseEntity<String> addRoleToUser(@RequestParam String userId, @RequestParam String roleName) {
        try {
            roleMappingServices.addRoleToUser(userId, roleName);
            return ResponseEntity.ok("Role added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding role: " + e.getMessage());
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeRoleFromUser(@RequestParam String userId, @RequestParam String roleName) {
        try {
            roleMappingServices.removeRoleFromUser(userId, roleName);
            return ResponseEntity.ok("Role removed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error removing role: " + e.getMessage());
        }
    }

    @GetMapping("/hasRole")
    public ResponseEntity<Boolean> hasUserRole(@RequestParam String userId, @RequestParam String roleName) {
        try {
            boolean hasRole = roleMappingServices.hasUserRole(userId, roleName);
            return ResponseEntity.ok(hasRole);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(false);
        }
    }
}
