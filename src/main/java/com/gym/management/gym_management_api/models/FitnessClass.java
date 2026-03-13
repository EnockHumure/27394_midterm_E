package com.gym.management.gym_management_api.models;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "fitness_classes")
@Data
public class FitnessClass {
    
    @Id
    private String id;
    
    @Column(name = "class_name", nullable = false)
    private String className;
    
    @Column(nullable = false)
    private String description;
    
    @Column(name = "schedule_time", nullable = false)
    private LocalDateTime scheduleTime;
    
    @Column(nullable = false)
    private Integer duration;
    
    @Column(name = "max_capacity", nullable = false)
    private Integer maxCapacity;
    
    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    @JsonIgnoreProperties({"members", "fitnessClasses"})
    private Trainer trainer;
    
    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
