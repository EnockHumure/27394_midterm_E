package com.gym.management.gym_management_api.controllers;

import com.gym.management.gym_management_api.models.Location;
import com.gym.management.gym_management_api.models.LocationRequest;
import com.gym.management.gym_management_api.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    
    @Autowired
    private LocationRepository locationRepository;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createLocation(@RequestBody LocationRequest request) {
        Location location = new Location();
        location.setCode(request.getCode());
        location.setName(request.getName());
        location.setLocationType(request.getLocationType());
        
        if (request.getParentId() != null) {
            Location parent = locationRepository.findById(request.getParentId()).orElse(null);
            location.setParent(parent);
        }
        
        Location saved = locationRepository.save(location);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Location created successfully");
        response.put("data", saved);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        return ResponseEntity.ok(locationRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable String id) {
        return locationRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/provinces")
    public ResponseEntity<List<Location>> getProvinces() {
        return ResponseEntity.ok(locationRepository.findByParentIsNull());
    }
    
    @GetMapping("/{parentId}/children")
    public ResponseEntity<List<Location>> getChildren(@PathVariable String parentId) {
        return ResponseEntity.ok(locationRepository.findByParentId(parentId));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateLocation(@PathVariable String id, @RequestBody LocationRequest request) {
        Location location = locationRepository.findById(id).orElse(null);
        if (location == null) {
            return ResponseEntity.notFound().build();
        }
        
        location.setCode(request.getCode());
        location.setName(request.getName());
        location.setLocationType(request.getLocationType());
        
        if (request.getParentId() != null) {
            Location parent = locationRepository.findById(request.getParentId()).orElse(null);
            location.setParent(parent);
        }
        
        Location updated = locationRepository.save(location);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Location updated successfully");
        response.put("data", updated);
        
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteLocation(@PathVariable String id) {
        if (!locationRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        locationRepository.deleteById(id);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Location deleted successfully");
        
        return ResponseEntity.ok(response);
    }
}
