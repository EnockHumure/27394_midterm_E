package com.gym.management.gym_management_api.controllers;

import com.gym.management.gym_management_api.models.Attendance;
import com.gym.management.gym_management_api.repositories.AttendanceRepository;
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
@RequestMapping("/api/attendances")
public class AttendanceController {
    
    @Autowired
    private AttendanceRepository attendanceRepository;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createAttendance(@RequestBody Attendance attendance) {
        Attendance saved = attendanceRepository.save(attendance);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Attendance recorded successfully");
        response.put("data", saved);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<Page<Attendance>> getAllAttendances(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(attendanceRepository.findAll(pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getAttendanceById(@PathVariable String id) {
        return attendanceRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/member/{memberId}")
    public ResponseEntity<Page<Attendance>> getAttendancesByMember(
            @PathVariable String memberId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(attendanceRepository.findByMemberId(memberId, pageable));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateAttendance(@PathVariable String id, @RequestBody Attendance attendance) {
        if (!attendanceRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        attendance.setId(id);
        Attendance updated = attendanceRepository.save(attendance);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Attendance updated successfully");
        response.put("data", updated);
        
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAttendance(@PathVariable String id) {
        if (!attendanceRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        attendanceRepository.deleteById(id);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Attendance deleted successfully");
        
        return ResponseEntity.ok(response);
    }
}
