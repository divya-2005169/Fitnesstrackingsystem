package com.fitness.service;

import com.fitness.entity.Payment;
import com.fitness.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService {
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }
    
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
    
    public List<Payment> getPaymentsByUserId(String userId) {
        return paymentRepository.findByUserId(userId);
    }
    
    public List<Payment> getPaymentsByDate(LocalDate date) {
        return paymentRepository.findByPaymentDate(date);
    }
    
    public List<Payment> getPaymentsByDateRange(LocalDate startDate, LocalDate endDate) {
        return paymentRepository.findByPaymentDateBetween(startDate, endDate);
    }
    
    public List<Payment> getTodayPayments() {
        return paymentRepository.findByPaymentDate(LocalDate.now());
    }
    
    public List<Payment> getYesterdayPayments() {
        return paymentRepository.findByPaymentDate(LocalDate.now().minusDays(1));
    }
    
    public List<Payment> getThisMonthPayments() {
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate endOfMonth = LocalDate.now();
        return paymentRepository.findByPaymentDateBetween(startOfMonth, endOfMonth);
    }
    
    public Payment createPayment(String userId, BigDecimal amount, LocalDate paymentDate) {
        Payment payment = new Payment(userId, amount, paymentDate);
        return paymentRepository.save(payment);
    }
} 