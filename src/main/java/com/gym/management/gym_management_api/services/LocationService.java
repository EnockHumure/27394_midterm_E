package com.gym.management.gym_management_api.services;

import com.gym.management.gym_management_api.models.Location;
import com.gym.management.gym_management_api.models.LocationRequest;
import com.gym.management.gym_management_api.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LocationService {
    
    @Autowired
    private LocationRepository locationRepository;
    
    public Location createLocation(LocationRequest request) {
        Location location = new Location();
        location.setCode(request.getCode());
        location.setName(request.getName());
        location.setLocationType(request.getLocationType());
        
        if (request.getParentId() != null) {
            Location parent = locationRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent location not found"));
            location.setParent(parent);
        }
        
        return locationRepository.save(location);
    }
    
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
    
    public Location getLocationById(String id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));
    }
    
    public List<Location> getProvinces() {
        return locationRepository.findByParentIsNull();
    }
    
    public List<Location> getChildren(String parentId) {
        return locationRepository.findByParentId(parentId);
    }
    
    public Location updateLocation(String id, LocationRequest request) {
        Location location = getLocationById(id);
        
        location.setCode(request.getCode());
        location.setName(request.getName());
        location.setLocationType(request.getLocationType());
        
        if (request.getParentId() != null) {
            Location parent = locationRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent location not found"));
            location.setParent(parent);
        }
        
        return locationRepository.save(location);
    }
    
    public void deleteLocation(String id) {
        if (!locationRepository.existsById(id)) {
            throw new RuntimeException("Location not found");
        }
        locationRepository.deleteById(id);
    }
}
