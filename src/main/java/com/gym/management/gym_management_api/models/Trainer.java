package com.gym.management.gym_management_api.models;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "trainers")
@Data
public class Trainer {
    
    @Id
    private String id;
    
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @Column(nullable = false)
    private String specialization;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @ManyToMany(mappedBy = "trainers")
    @JsonIgnore
    private List<Member> members;
    
    @OneToMany(mappedBy = "trainer")
    @JsonIgnore
    private List<FitnessClass> fitnessClasses;
    
    // Calculated field - not stored in database
    @Transient
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
