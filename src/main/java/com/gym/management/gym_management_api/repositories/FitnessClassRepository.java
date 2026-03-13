package com.gym.management.gym_management_api.repositories;

import com.gym.management.gym_management_api.models.FitnessClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FitnessClassRepository extends JpaRepository<FitnessClass, String> {
    List<FitnessClass> findByTrainerId(String trainerId);
}
