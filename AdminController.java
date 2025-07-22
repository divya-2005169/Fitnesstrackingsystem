package com.fitness.controller;

import com.fitness.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    
    @Autowired
    private AdminService adminService;
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");
        
        boolean isAuthenticated = adminService.authenticateAdmin(username, password);
        
        Map<String, Object> response = new HashMap<>();
        if (isAuthenticated) {
            response.put("success", true);
            response.put("message", "Login successful");
        } else {
            response.put("success", false);
            response.put("message", "Invalid username or password");
        }
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createAdmin(@RequestBody Map<String, String> adminRequest) {
        String username = adminRequest.get("username");
        String password = adminRequest.get("password");
        
        try {
            adminService.createAdmin(username, password);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Admin created successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Failed to create admin: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
} 