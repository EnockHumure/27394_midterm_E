package com.gym.management.gym_management_api.repositories;

import com.gym.management.gym_management_api.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, String> {
}
