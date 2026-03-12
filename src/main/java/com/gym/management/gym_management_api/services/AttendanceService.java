package com.gym.management.gym_management_api.services;

import com.gym.management.gym_management_api.models.Attendance;
import com.gym.management.gym_management_api.repositories.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {
    
    @Autowired
    private AttendanceRepository attendanceRepository;
    
    public Attendance createAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }
    
    public Page<Attendance> getAllAttendances(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return attendanceRepository.findAll(pageable);
    }
    
    public Attendance getAttendanceById(String id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));
    }
    
    public Page<Attendance> getAttendancesByMember(String memberId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return attendanceRepository.findByMemberId(memberId, pageable);
    }
    
    public Attendance updateAttendance(String id, Attendance attendance) {
        if (!attendanceRepository.existsById(id)) {
            throw new RuntimeException("Attendance not found");
        }
        attendance.setId(id);
        return attendanceRepository.save(attendance);
    }
    
    public void deleteAttendance(String id) {
        if (!attendanceRepository.existsById(id)) {
            throw new RuntimeException("Attendance not found");
        }
        attendanceRepository.deleteById(id);
    }
}
