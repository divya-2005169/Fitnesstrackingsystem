package com.fitness.controller;

import com.fitness.entity.Member;
import com.fitness.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*")
public class MemberController {
    
    @Autowired
    private MemberService memberService;
    
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addMember(@RequestBody Member member) {
        try {
            if (memberService.existsByUserId(member.getUserId())) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "User ID already exists");
                return ResponseEntity.badRequest().body(response);
            }
            
            Member savedMember = memberService.addMember(member);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Member added successfully");
            response.put("member", savedMember);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Failed to add member: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getMemberByUserId(@PathVariable String userId) {
        try {
            var memberOpt = memberService.getMemberByUserId(userId);
            Map<String, Object> response = new HashMap<>();
            
            if (memberOpt.isPresent()) {
                response.put("success", true);
                response.put("member", memberOpt.get());
            } else {
                response.put("success", false);
                response.put("message", "Member not found");
            }
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateMember(@RequestBody Member member) {
        try {
            Member updatedMember = memberService.updateMember(member);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Member updated successfully");
            response.put("member", updatedMember);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Failed to update member: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @PutMapping("/workout")
    public ResponseEntity<Map<String, Object>> updateWorkoutDetails(@RequestBody Map<String, String> workoutRequest) {
        String userId = workoutRequest.get("userId");
        String workoutType = workoutRequest.get("workoutType");
        String workoutDuration = workoutRequest.get("workoutDuration");
        
        try {
            Member updatedMember = memberService.updateWorkoutDetails(userId, workoutType, workoutDuration);
            Map<String, Object> response = new HashMap<>();
            
            if (updatedMember != null) {
                response.put("success", true);
                response.put("message", "Workout details updated successfully");
                response.put("member", updatedMember);
            } else {
                response.put("success", false);
                response.put("message", "Member not found");
            }
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Failed to update workout details: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteMember(@PathVariable Long id) {
        try {
            memberService.deleteMember(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Member deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Failed to delete member: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
} 