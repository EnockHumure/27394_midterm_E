package com.gym.management.gym_management_api.models;

import lombok.Data;

@Data
public class LocationRequest {
    private String code;
    private String name;
    private Location.LocationType locationType;
    private String parentId;
}
