package com.gym.management.gym_management_api.controllers;

import com.gym.management.gym_management_api.models.Member;
import com.gym.management.gym_management_api.models.Trainer;
import com.gym.management.gym_management_api.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private TrainerService trainerService;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createTrainer(@RequestBody Trainer trainer) {
        try {
            Trainer saved = trainerService.createTrainer(trainer);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Trainer created successfully");
            response.put("data", saved);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
    
    @GetMapping
    public ResponseEntity<Page<Trainer>> getAllTrainers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(trainerService.getAllTrainers(page, size));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(trainerService.getTrainerById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{trainerId}/members")
    public ResponseEntity<List<Member>> getTrainerMembers(@PathVariable String trainerId) {
        try {
            Trainer trainer = trainerService.getTrainerById(trainerId);
            return ResponseEntity.ok(trainer.getMembers());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateTrainer(@PathVariable String id, @RequestBody Trainer trainer) {
        try {
            Trainer updated = trainerService.updateTrainer(id, trainer);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Trainer updated successfully");
            response.put("data", updated);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTrainer(@PathVariable String id) {
        try {
            trainerService.deleteTrainer(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Trainer deleted successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
