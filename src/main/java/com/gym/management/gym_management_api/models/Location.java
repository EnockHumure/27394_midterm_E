package com.gym.management.gym_management_api.models;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "locations")
@Data
public class Location {
    
    @Id
    private String id;
    
    @Column(unique = true, nullable = false)
    private String code;
    
    @Column(nullable = false)
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "location_type", nullable = false)
    private LocationType locationType;
    
    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnoreProperties({"parent", "children"})
    private Location parent;
    
    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<Location> children;
    
    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
    
    public enum LocationType {
        PROVINCE, DISTRICT, SECTOR, CELL, VILLAGE
    }
}
