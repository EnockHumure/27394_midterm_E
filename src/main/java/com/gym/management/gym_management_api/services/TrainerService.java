package com.gym.management.gym_management_api.services;

import com.gym.management.gym_management_api.models.Trainer;
import com.gym.management.gym_management_api.repositories.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {
    
    @Autowired
    private TrainerRepository trainerRepository;
    
    public Trainer createTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }
    
    public Page<Trainer> getAllTrainers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return trainerRepository.findAll(pageable);
    }
    
    public Trainer getTrainerById(String id) {
        return trainerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));
    }
    
    public Trainer updateTrainer(String id, Trainer trainer) {
        if (!trainerRepository.existsById(id)) {
            throw new RuntimeException("Trainer not found");
        }
        trainer.setId(id);
        return trainerRepository.save(trainer);
    }
    
    public void deleteTrainer(String id) {
        if (!trainerRepository.existsById(id)) {
            throw new RuntimeException("Trainer not found");
        }
        trainerRepository.deleteById(id);
    }
}
