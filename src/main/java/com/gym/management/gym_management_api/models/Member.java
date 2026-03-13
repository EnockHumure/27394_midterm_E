package com.gym.management.gym_management_api.models;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "members")
@Data
public class Member {
    
    @Id
    private String id;
    
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    private String phone;
    
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    @JsonIgnoreProperties({"parent", "children"})
    private Location location;
    
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"member", "payments"})
    private Membership membership;
    
    @ManyToMany
    @JoinTable(
        name = "member_trainer",
        joinColumns = @JoinColumn(name = "member_id"),
        inverseJoinColumns = @JoinColumn(name = "trainer_id")
    )
    @JsonIgnoreProperties({"members", "fitnessClasses"})
    private List<Trainer> trainers;
    
    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Attendance> attendances;
    
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
