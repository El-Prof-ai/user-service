package com.bansikah.keycloakdemo.controller;

import com.bansikah.keycloakdemo.service.keycloak.RolesServices;
import lombok.AllArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@AllArgsConstructor
public class RolesController {

    private final RolesServices rolesServices;

    @PostMapping("/create")
    public ResponseEntity<String> createRole(@RequestParam String roleName) {
        try {
            rolesServices.createRole(roleName);
            return ResponseEntity.ok("Role created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating role: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllRoles() {
        try {
            List<String> roles = rolesServices.getAllRoles();
            return ResponseEntity.ok(roles);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(List.of());
        }
    }

    @GetMapping("/{roleName}")
    public ResponseEntity<RoleRepresentation> getRole(@PathVariable String roleName) {
        try {
            RoleRepresentation role = rolesServices.getRole(roleName);
            return ResponseEntity.ok(role);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateRole(@RequestParam String roleName, @RequestParam String newDescription) {
        try {
            rolesServices.updateRole(roleName, newDescription);
            return ResponseEntity.ok("Role updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating role: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteRole(@RequestParam String roleName) {
        try {
            rolesServices.deleteRole(roleName);
            return ResponseEntity.ok("Role deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting role: " + e.getMessage());
        }
    }
}
