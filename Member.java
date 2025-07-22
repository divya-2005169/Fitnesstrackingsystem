package com.fitness.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "MEMBERS")
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "USER_ID", unique = true, nullable = false)
    private String userId;
    
    @Column(name = "NAME", nullable = false)
    private String name;
    
    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;
    
    @Column(name = "WEIGHT")
    private Double weight;
    
    @Column(name = "HEIGHT")
    private Double height;
    
    @Column(name = "WORKOUT_SCHEDULE")
    private String workoutSchedule;
    
    @Column(name = "WORKOUT_TYPE")
    private String workoutType;
    
    @Column(name = "WORKOUT_DURATION")
    private String workoutDuration;
    
    @Column(name = "JOIN_DATE")
    private LocalDate joinDate;
    
    public Member() {
        this.joinDate = LocalDate.now();
    }
    
    public Member(String userId, String name, String phoneNumber, Double weight, 
                  Double height, String workoutSchedule) {
        this();
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.weight = weight;
        this.height = height;
        this.workoutSchedule = workoutSchedule;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public Double getWeight() {
        return weight;
    }
    
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    
    public Double getHeight() {
        return height;
    }
    
    public void setHeight(Double height) {
        this.height = height;
    }
    
    public String getWorkoutSchedule() {
        return workoutSchedule;
    }
    
    public void setWorkoutSchedule(String workoutSchedule) {
        this.workoutSchedule = workoutSchedule;
    }
    
    public String getWorkoutType() {
        return workoutType;
    }
    
    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }
    
    public String getWorkoutDuration() {
        return workoutDuration;
    }
    
    public void setWorkoutDuration(String workoutDuration) {
        this.workoutDuration = workoutDuration;
    }
    
    public LocalDate getJoinDate() {
        return joinDate;
    }
    
    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }
} 