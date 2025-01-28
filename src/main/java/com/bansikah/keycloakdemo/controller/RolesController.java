package com.bansikah.keycloakdemo.controller;

import com.bansikah.keycloakdemo.service.keycloak.RolesServices;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RolesServices roleService;

    // Create a new role
    @PostMapping
    public ResponseEntity<?> createRole(@RequestParam String roleName) {
        roleService.createRole(roleName);
        return ResponseEntity.ok("Role created successfully");
    }

    // Get all roles
    @GetMapping
    public ResponseEntity<List<String>> getAllRoles() {
        List<String> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    // Get details of a specific role
    @GetMapping("/{roleName}")
    public ResponseEntity<RoleRepresentation> getRole(@PathVariable String roleName) {
        RoleRepresentation role = roleService.getRole(roleName);
        return ResponseEntity.ok(role);
    }

    // Update a role
    @PutMapping("/{roleName}")
    public ResponseEntity<?> updateRole(@PathVariable String roleName, @RequestParam String description) {
        roleService.updateRole(roleName, description);
        return ResponseEntity.ok("Role updated successfully");
    }

    // Delete a role
    @DeleteMapping("/{roleName}")
    public ResponseEntity<?> deleteRole(@PathVariable String roleName) {
        roleService.deleteRole(roleName);
        return ResponseEntity.ok("Role deleted successfully");
    }
}
