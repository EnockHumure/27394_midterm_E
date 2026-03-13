package com.gym.management.gym_management_api.repositories;

import com.gym.management.gym_management_api.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {
    List<Location> findByParentIsNull();
    List<Location> findByLocationType(Location.LocationType locationType);
    List<Location> findByParentId(String parentId);
}
