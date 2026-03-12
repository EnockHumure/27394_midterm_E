package com.gym.management.gym_management_api.controllers;

import com.gym.management.gym_management_api.models.Location;
import com.gym.management.gym_management_api.models.LocationRequest;
import com.gym.management.gym_management_api.services.LocationService;
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
    private LocationService locationService;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createLocation(@RequestBody LocationRequest request) {
        try {
            Location saved = locationService.createLocation(request);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Location created successfully");
            response.put("data", saved);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(locationService.getLocationById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/provinces")
    public ResponseEntity<List<Location>> getProvinces() {
        return ResponseEntity.ok(locationService.getProvinces());
    }
    
    @GetMapping("/{parentId}/children")
    public ResponseEntity<List<Location>> getChildren(@PathVariable String parentId) {
        return ResponseEntity.ok(locationService.getChildren(parentId));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateLocation(@PathVariable String id, @RequestBody LocationRequest request) {
        try {
            Location updated = locationService.updateLocation(id, request);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Location updated successfully");
            response.put("data", updated);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteLocation(@PathVariable String id) {
        try {
            locationService.deleteLocation(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Location deleted successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
