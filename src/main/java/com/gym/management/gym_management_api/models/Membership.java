package com.gym.management.gym_management_api.models;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "memberships")
@Data
public class Membership {
    
    @Id
    private String id;
    
    @OneToOne
    @JoinColumn(name = "member_id", unique = true, nullable = false)
    @JsonIgnoreProperties({"membership", "trainers", "attendances"})
    private Member member;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "plan_type", nullable = false)
    private PlanType planType;
    
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MembershipStatus status;
    
    @OneToMany(mappedBy = "membership", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Payment> payments;
    
    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
    
    public enum PlanType {
        BASIC, PREMIUM, VIP
    }
    
    public enum MembershipStatus {
        ACTIVE, EXPIRED
    }
}
