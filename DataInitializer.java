package com.fitness.config;

import com.fitness.entity.Admin;
import com.fitness.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create default admin if not exists
        if (!adminRepository.findByUsername("admin").isPresent()) {
            Admin admin = new Admin("admin", "admin123");
            adminRepository.save(admin);
            System.out.println("Default admin created: username=admin, password=admin123");
        }
        System.out.println("Fitness Tracker Application Started Successfully!");
    }
} 