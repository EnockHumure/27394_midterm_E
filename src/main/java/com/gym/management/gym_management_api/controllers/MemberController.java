package com.gym.management.gym_management_api.controllers;

import com.gym.management.gym_management_api.models.Member;
import com.gym.management.gym_management_api.models.Trainer;
import com.gym.management.gym_management_api.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    
    @Autowired
    private MemberService memberService;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createMember(@RequestBody Member member) {
        try {
            Member saved = memberService.createMember(member);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Member created successfully");
            response.put("data", saved);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
    
    @GetMapping
    public ResponseEntity<Page<Member>> getAllMembers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "firstName") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(memberService.getAllMembers(page, size, sort, direction));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(memberService.getMemberById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmailExists(@RequestParam String email) {
        return ResponseEntity.ok(memberService.checkEmailExists(email));
    }
    
    @GetMapping("/province/{name}")
    public ResponseEntity<Page<Member>> getMembersByProvince(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(memberService.getMembersByProvince(name, page, size));
    }
    
    @GetMapping("/district/{name}")
    public ResponseEntity<Page<Member>> getMembersByDistrict(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(memberService.getMembersByDistrict(name, page, size));
    }
    
    @GetMapping("/sector/{name}")
    public ResponseEntity<Page<Member>> getMembersBySector(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(memberService.getMembersBySector(name, page, size));
    }
    
    @GetMapping("/cell/{name}")
    public ResponseEntity<Page<Member>> getMembersByCell(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(memberService.getMembersByCell(name, page, size));
    }
    
    @GetMapping("/village/{name}")
    public ResponseEntity<Page<Member>> getMembersByVillage(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(memberService.getMembersByVillage(name, page, size));
    }
    
    @PostMapping("/{memberId}/trainers/{trainerId}")
    public ResponseEntity<Map<String, String>> assignTrainer(
            @PathVariable String memberId,
            @PathVariable String trainerId) {
        try {
            memberService.assignTrainer(memberId, trainerId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Trainer assigned successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
    
    @GetMapping("/{memberId}/trainers")
    public ResponseEntity<List<Trainer>> getMemberTrainers(@PathVariable String memberId) {
        try {
            Member member = memberService.getMemberById(memberId);
            return ResponseEntity.ok(member.getTrainers());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateMember(@PathVariable String id, @RequestBody Member member) {
        try {
            Member updated = memberService.updateMember(id, member);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Member updated successfully");
            response.put("data", updated);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteMember(@PathVariable String id) {
        try {
            memberService.deleteMember(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Member deleted successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
