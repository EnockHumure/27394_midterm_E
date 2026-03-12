package com.gym.management.gym_management_api.services;

import com.gym.management.gym_management_api.models.FitnessClass;
import com.gym.management.gym_management_api.repositories.FitnessClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FitnessClassService {
    
    @Autowired
    private FitnessClassRepository fitnessClassRepository;
    
    public FitnessClass createFitnessClass(FitnessClass fitnessClass) {
        return fitnessClassRepository.save(fitnessClass);
    }
    
    public Page<FitnessClass> getAllFitnessClasses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return fitnessClassRepository.findAll(pageable);
    }
    
    public FitnessClass getFitnessClassById(String id) {
        return fitnessClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fitness class not found"));
    }
    
    public List<FitnessClass> getFitnessClassesByTrainer(String trainerId) {
        return fitnessClassRepository.findByTrainerId(trainerId);
    }
    
    public FitnessClass updateFitnessClass(String id, FitnessClass fitnessClass) {
        if (!fitnessClassRepository.existsById(id)) {
            throw new RuntimeException("Fitness class not found");
        }
        fitnessClass.setId(id);
        return fitnessClassRepository.save(fitnessClass);
    }
    
    public void deleteFitnessClass(String id) {
        if (!fitnessClassRepository.existsById(id)) {
            throw new RuntimeException("Fitness class not found");
        }
        fitnessClassRepository.deleteById(id);
    }
}
