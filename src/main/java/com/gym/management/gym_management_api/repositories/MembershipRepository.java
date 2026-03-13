package com.gym.management.gym_management_api.repositories;

import com.gym.management.gym_management_api.models.Membership;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, String> {
    Optional<Membership> findByMemberId(String memberId);
    
    Page<Membership> findByStatus(Membership.MembershipStatus status, Pageable pageable);
    
    Page<Membership> findByPlanType(Membership.PlanType planType, Pageable pageable);
    
    long countByStatus(Membership.MembershipStatus status);
}
