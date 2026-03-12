package com.gym.management.gym_management_api.controllers;

import com.gym.management.gym_management_api.models.Membership;
import com.gym.management.gym_management_api.services.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/memberships")
public class MembershipController {
    
    @Autowired
    private MembershipService membershipService;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createMembership(@RequestBody Membership membership) {
        try {
            Membership saved = membershipService.createMembership(membership);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Membership created successfully");
            response.put("data", saved);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
    
    @GetMapping
    public ResponseEntity<Page<Membership>> getAllMemberships(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(membershipService.getAllMemberships(page, size));
    }
    
    @GetMapping("/member/{memberId}")
    public ResponseEntity<Membership> getMembershipByMemberId(@PathVariable String memberId) {
        try {
            return ResponseEntity.ok(membershipService.getMembershipByMemberId(memberId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<Page<Membership>> getMembershipsByStatus(
            @PathVariable String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            return ResponseEntity.ok(membershipService.getMembershipsByStatus(status, page, size));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/plan/{planType}")
    public ResponseEntity<Page<Membership>> getMembershipsByPlanType(
            @PathVariable String planType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            return ResponseEntity.ok(membershipService.getMembershipsByPlanType(planType, page, size));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/status/{status}/count")
    public ResponseEntity<Map<String, Object>> countByStatus(@PathVariable String status) {
        try {
            long count = membershipService.countByStatus(status);
            Map<String, Object> response = new HashMap<>();
            response.put("status", status);
            response.put("count", count);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateMembership(@PathVariable String id, @RequestBody Membership membership) {
        try {
            Membership updated = membershipService.updateMembership(id, membership);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Membership updated successfully");
            response.put("data", updated);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteMembership(@PathVariable String id) {
        try {
            membershipService.deleteMembership(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Membership deleted successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
