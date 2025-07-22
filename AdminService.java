package com.fitness.service;

import com.fitness.entity.Admin;
import com.fitness.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    
    @Autowired
    private AdminRepository adminRepository;
    
    public boolean authenticateAdmin(String username, String password) {
        Optional<Admin> admin = adminRepository.findByUsernameAndPassword(username, password);
        return admin.isPresent();
    }
    
    public Admin createAdmin(String username, String password) {
        Admin admin = new Admin(username, password);
        return adminRepository.save(admin);
    }
    
    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
} 