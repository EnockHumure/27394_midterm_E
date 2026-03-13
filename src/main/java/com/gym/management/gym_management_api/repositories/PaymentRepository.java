package com.gym.management.gym_management_api.repositories;

import com.gym.management.gym_management_api.models.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
    List<Payment> findByMembershipId(String membershipId);
    
    Page<Payment> findByMembershipId(String membershipId, Pageable pageable);
}
