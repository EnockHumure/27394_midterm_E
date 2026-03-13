package com.gym.management.gym_management_api.repositories;

import com.gym.management.gym_management_api.models.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, String> {
    List<Attendance> findByMemberId(String memberId);
    
    Page<Attendance> findByMemberId(String memberId, Pageable pageable);
}
