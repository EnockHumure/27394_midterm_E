package com.gym.management.gym_management_api.controllers;

import com.gym.management.gym_management_api.models.Membership;
import com.gym.management.gym_management_api.repositories.MembershipRepository;
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
@RequestMapping("/api/memberships")
public class MembershipController {
    
    @Autowired
    private MembershipRepository membershipRepository;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createMembership(@RequestBody Membership membership) {
        Membership saved = membershipRepository.save(membership);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Membership created successfully");
        response.put("data", saved);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<Page<Membership>> getAllMemberships(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(membershipRepository.findAll(pageable));
    }
    
    @GetMapping("/member/{memberId}")
    public ResponseEntity<Membership> getMembershipByMemberId(@PathVariable String memberId) {
        return membershipRepository.findByMemberId(memberId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<Page<Membership>> getMembershipsByStatus(
            @PathVariable String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Membership.MembershipStatus membershipStatus = Membership.MembershipStatus.valueOf(status.toUpperCase());
            Pageable pageable = PageRequest.of(page, size);
            return ResponseEntity.ok(membershipRepository.findByStatus(membershipStatus, pageable));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/plan/{planType}")
    public ResponseEntity<Page<Membership>> getMembershipsByPlanType(
            @PathVariable String planType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Membership.PlanType plan = Membership.PlanType.valueOf(planType.toUpperCase());
            Pageable pageable = PageRequest.of(page, size);
            return ResponseEntity.ok(membershipRepository.findByPlanType(plan, pageable));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/status/{status}/count")
    public ResponseEntity<Map<String, Object>> countByStatus(@PathVariable String status) {
        try {
            Membership.MembershipStatus membershipStatus = Membership.MembershipStatus.valueOf(status.toUpperCase());
            long count = membershipRepository.countByStatus(membershipStatus);
            
            Map<String, Object> response = new HashMap<>();
            response.put("status", status);
            response.put("count", count);
            
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateMembership(@PathVariable String id, @RequestBody Membership membership) {
        if (!membershipRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        membership.setId(id);
        Membership updated = membershipRepository.save(membership);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Membership updated successfully");
        response.put("data", updated);
        
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteMembership(@PathVariable String id) {
        if (!membershipRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        membershipRepository.deleteById(id);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Membership deleted successfully");
        
        return ResponseEntity.ok(response);
    }
}
