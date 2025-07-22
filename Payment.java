package com.fitness.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "PAYMENTS")
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "USER_ID", nullable = false)
    private String userId;
    
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;
    
    @Column(name = "PAYMENT_DATE", nullable = false)
    private LocalDate paymentDate;
    
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    
    public Payment() {
        this.paymentDate = LocalDate.now();
    }
    
    public Payment(String userId, BigDecimal amount, LocalDate paymentDate) {
        this();
        this.userId = userId;
        this.amount = amount;
        this.paymentDate = paymentDate;
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
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public LocalDate getPaymentDate() {
        return paymentDate;
    }
    
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    public Member getMember() {
        return member;
    }
    
    public void setMember(Member member) {
        this.member = member;
    }
} 