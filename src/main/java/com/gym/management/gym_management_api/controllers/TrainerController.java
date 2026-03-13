package com.gym.management.gym_management_api.controllers;

import com.gym.management.gym_management_api.models.Member;
import com.gym.management.gym_management_api.models.Trainer;
import com.gym.management.gym_management_api.repositories.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {
    
    @Autowired
    private TrainerRepository trainerRepository;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createTrainer(@RequestBody Trainer trainer) {
        Trainer saved = trainerRepository.save(trainer);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Trainer created successfully");
        response.put("data", saved);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<Page<Trainer>> getAllTrainers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(trainerRepository.findAll(pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable String id) {
        return trainerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{trainerId}/members")
    public ResponseEntity<List<Member>> getTrainerMembers(@PathVariable String trainerId) {
        return trainerRepository.findById(trainerId)
                .map(trainer -> ResponseEntity.ok(trainer.getMembers()))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateTrainer(@PathVariable String id, @RequestBody Trainer trainer) {
        if (!trainerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        trainer.setId(id);
        Trainer updated = trainerRepository.save(trainer);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Trainer updated successfully");
        response.put("data", updated);
        
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTrainer(@PathVariable String id) {
        if (!trainerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        trainerRepository.deleteById(id);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Trainer deleted successfully");
        
        return ResponseEntity.ok(response);
    }
}
