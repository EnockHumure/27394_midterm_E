package com.gym.management.gym_management_api.controllers;

import com.gym.management.gym_management_api.models.Member;
import com.gym.management.gym_management_api.models.Trainer;
import com.gym.management.gym_management_api.repositories.MemberRepository;
import com.gym.management.gym_management_api.repositories.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private MemberRepository memberRepository;
    
    @Autowired
    private TrainerRepository trainerRepository;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createMember(@RequestBody Member member) {
        if (memberRepository.existsByEmail(member.getEmail())) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Email already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        
        Member saved = memberRepository.save(member);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Member created successfully");
        response.put("data", saved);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<Page<Member>> getAllMembers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "firstName") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort));
        
        return ResponseEntity.ok(memberRepository.findAll(pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable String id) {
        return memberRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmailExists(@RequestParam String email) {
        return ResponseEntity.ok(memberRepository.existsByEmail(email));
    }
    
    @GetMapping("/province/{name}")
    public ResponseEntity<Page<Member>> getMembersByProvince(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(memberRepository.findByProvinceName(name, pageable));
    }
    
    @GetMapping("/district/{name}")
    public ResponseEntity<Page<Member>> getMembersByDistrict(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(memberRepository.findByDistrictName(name, pageable));
    }
    
    @GetMapping("/sector/{name}")
    public ResponseEntity<Page<Member>> getMembersBySector(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(memberRepository.findBySectorName(name, pageable));
    }
    
    @GetMapping("/cell/{name}")
    public ResponseEntity<Page<Member>> getMembersByCell(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(memberRepository.findByCellName(name, pageable));
    }
    
    @GetMapping("/village/{name}")
    public ResponseEntity<Page<Member>> getMembersByVillage(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(memberRepository.findByVillageName(name, pageable));
    }
    
    @PostMapping("/{memberId}/trainers/{trainerId}")
    public ResponseEntity<Map<String, String>> assignTrainer(
            @PathVariable String memberId,
            @PathVariable String trainerId) {
        
        Member member = memberRepository.findById(memberId).orElse(null);
        Trainer trainer = trainerRepository.findById(trainerId).orElse(null);
        
        if (member == null || trainer == null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Member or Trainer not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        
        member.getTrainers().add(trainer);
        memberRepository.save(member);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Trainer assigned successfully");
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{memberId}/trainers")
    public ResponseEntity<List<Trainer>> getMemberTrainers(@PathVariable String memberId) {
        return memberRepository.findById(memberId)
                .map(member -> ResponseEntity.ok(member.getTrainers()))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateMember(@PathVariable String id, @RequestBody Member member) {
        if (!memberRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        member.setId(id);
        Member updated = memberRepository.save(member);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Member updated successfully");
        response.put("data", updated);
        
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteMember(@PathVariable String id) {
        if (!memberRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        memberRepository.deleteById(id);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Member deleted successfully");
        
        return ResponseEntity.ok(response);
    }
}
