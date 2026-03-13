package com.gym.management.gym_management_api.controllers;

import com.gym.management.gym_management_api.models.Payment;
import com.gym.management.gym_management_api.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createPayment(@RequestBody Payment payment) {
        Payment saved = paymentRepository.save(payment);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Payment recorded successfully");
        response.put("data", saved);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<Page<Payment>> getAllPayments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(paymentRepository.findAll(pageable));
    }
    
    @GetMapping("/membership/{membershipId}")
    public ResponseEntity<Page<Payment>> getPaymentsByMembership(
            @PathVariable String membershipId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(paymentRepository.findByMembershipId(membershipId, pageable));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updatePayment(@PathVariable String id, @RequestBody Payment payment) {
        if (!paymentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        payment.setId(id);
        Payment updated = paymentRepository.save(payment);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Payment updated successfully");
        response.put("data", updated);
        
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletePayment(@PathVariable String id) {
        if (!paymentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        paymentRepository.deleteById(id);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Payment deleted successfully");
        
        return ResponseEntity.ok(response);
    }
}
