package com.gym.management.gym_management_api.controllers;

import com.gym.management.gym_management_api.models.FitnessClass;
import com.gym.management.gym_management_api.services.FitnessClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private FitnessClassService fitnessClassService;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createFitnessClass(@RequestBody FitnessClass fitnessClass) {
        try {
            FitnessClass saved = fitnessClassService.createFitnessClass(fitnessClass);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Fitness class created successfully");
            response.put("data", saved);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
    
    @GetMapping
    public ResponseEntity<Page<FitnessClass>> getAllFitnessClasses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(fitnessClassService.getAllFitnessClasses(page, size));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FitnessClass> getFitnessClassById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(fitnessClassService.getFitnessClassById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<List<FitnessClass>> getFitnessClassesByTrainer(@PathVariable String trainerId) {
        return ResponseEntity.ok(fitnessClassService.getFitnessClassesByTrainer(trainerId));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateFitnessClass(@PathVariable String id, @RequestBody FitnessClass fitnessClass) {
        try {
            FitnessClass updated = fitnessClassService.updateFitnessClass(id, fitnessClass);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Fitness class updated successfully");
            response.put("data", updated);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteFitnessClass(@PathVariable String id) {
        try {
            fitnessClassService.deleteFitnessClass(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Fitness class deleted successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
