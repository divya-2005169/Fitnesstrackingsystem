package com.fitness.controller;

import com.fitness.entity.Payment;
import com.fitness.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {
    
    @Autowired
    private PaymentService paymentService;
    
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addPayment(@RequestBody Map<String, Object> paymentRequest) {
        try {
            String userId = (String) paymentRequest.get("userId");
            BigDecimal amount = new BigDecimal(paymentRequest.get("amount").toString());
            LocalDate paymentDate = LocalDate.parse((String) paymentRequest.get("paymentDate"));
            
            Payment payment = paymentService.createPayment(userId, amount, paymentDate);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Payment added successfully");
            response.put("payment", payment);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Failed to add payment: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payment>> getPaymentsByUserId(@PathVariable String userId) {
        List<Payment> payments = paymentService.getPaymentsByUserId(userId);
        return ResponseEntity.ok(payments);
    }
    
    @GetMapping("/today")
    public ResponseEntity<List<Payment>> getTodayPayments() {
        List<Payment> payments = paymentService.getTodayPayments();
        return ResponseEntity.ok(payments);
    }
    
    @GetMapping("/yesterday")
    public ResponseEntity<List<Payment>> getYesterdayPayments() {
        List<Payment> payments = paymentService.getYesterdayPayments();
        return ResponseEntity.ok(payments);
    }
    
    @GetMapping("/this-month")
    public ResponseEntity<List<Payment>> getThisMonthPayments() {
        List<Payment> payments = paymentService.getThisMonthPayments();
        return ResponseEntity.ok(payments);
    }
    
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Payment>> getPaymentsByDate(@PathVariable String date) {
        LocalDate paymentDate = LocalDate.parse(date);
        List<Payment> payments = paymentService.getPaymentsByDate(paymentDate);
        return ResponseEntity.ok(payments);
    }
    
    @GetMapping("/date-range")
    public ResponseEntity<List<Payment>> getPaymentsByDateRange(
            @RequestParam String startDate, 
            @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<Payment> payments = paymentService.getPaymentsByDateRange(start, end);
        return ResponseEntity.ok(payments);
    }
    
    @GetMapping("/filter")
    public ResponseEntity<List<Payment>> getPaymentsByFilter(@RequestParam String filter) {
        List<Payment> payments;
        switch (filter.toLowerCase()) {
            case "today":
                payments = paymentService.getTodayPayments();
                break;
            case "yesterday":
                payments = paymentService.getYesterdayPayments();
                break;
            case "this-month":
                payments = paymentService.getThisMonthPayments();
                break;
            case "all":
                payments = paymentService.getAllPayments();
                break;
            default:
                payments = paymentService.getAllPayments();
        }
        return ResponseEntity.ok(payments);
    }
} 