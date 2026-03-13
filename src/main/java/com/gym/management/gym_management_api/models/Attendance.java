package com.gym.management.gym_management_api.models;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "attendances")
@Data
public class Attendance {
    
    @Id
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    @JsonIgnoreProperties({"membership", "trainers", "attendances", "location"})
    private Member member;
    
    @Column(name = "check_in_time", nullable = false)
    private LocalDateTime checkInTime;
    
    @Column(name = "check_out_time")
    private LocalDateTime checkOutTime;
    
    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
