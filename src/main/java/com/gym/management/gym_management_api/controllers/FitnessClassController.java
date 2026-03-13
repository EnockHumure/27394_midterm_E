package com.gym.management.gym_management_api.controllers;

import com.gym.management.gym_management_api.models.FitnessClass;
import com.gym.management.gym_management_api.repositories.FitnessClassRepository;
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
@RequestMapping("/api/fitness-classes")
public class FitnessClassController {
    
    @Autowired
    private FitnessClassRepository fitnessClassRepository;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createFitnessClass(@RequestBody FitnessClass fitnessClass) {
        FitnessClass saved = fitnessClassRepository.save(fitnessClass);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Fitness class created successfully");
        response.put("data", saved);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<Page<FitnessClass>> getAllFitnessClasses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(fitnessClassRepository.findAll(pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FitnessClass> getFitnessClassById(@PathVariable String id) {
        return fitnessClassRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<List<FitnessClass>> getFitnessClassesByTrainer(@PathVariable String trainerId) {
        return ResponseEntity.ok(fitnessClassRepository.findByTrainerId(trainerId));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateFitnessClass(@PathVariable String id, @RequestBody FitnessClass fitnessClass) {
        if (!fitnessClassRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        fitnessClass.setId(id);
        FitnessClass updated = fitnessClassRepository.save(fitnessClass);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Fitness class updated successfully");
        response.put("data", updated);
        
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteFitnessClass(@PathVariable String id) {
        if (!fitnessClassRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        fitnessClassRepository.deleteById(id);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Fitness class deleted successfully");
        
        return ResponseEntity.ok(response);
    }
}
