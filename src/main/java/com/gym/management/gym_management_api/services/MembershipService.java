package com.gym.management.gym_management_api.services;

import com.gym.management.gym_management_api.models.Membership;
import com.gym.management.gym_management_api.repositories.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MembershipService {
    
    @Autowired
    private MembershipRepository membershipRepository;
    
    public Membership createMembership(Membership membership) {
        return membershipRepository.save(membership);
    }
    
    public Page<Membership> getAllMemberships(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return membershipRepository.findAll(pageable);
    }
    
    public Membership getMembershipByMemberId(String memberId) {
        return membershipRepository.findByMemberId(memberId)
                .orElseThrow(() -> new RuntimeException("Membership not found for member"));
    }
    
    public Page<Membership> getMembershipsByStatus(String status, int page, int size) {
        try {
            Membership.MembershipStatus membershipStatus = Membership.MembershipStatus.valueOf(status.toUpperCase());
            Pageable pageable = PageRequest.of(page, size);
            return membershipRepository.findByStatus(membershipStatus, pageable);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status: " + status);
        }
    }
    
    public Page<Membership> getMembershipsByPlanType(String planType, int page, int size) {
        try {
            Membership.PlanType plan = Membership.PlanType.valueOf(planType.toUpperCase());
            Pageable pageable = PageRequest.of(page, size);
            return membershipRepository.findByPlanType(plan, pageable);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid plan type: " + planType);
        }
    }
    
    public long countByStatus(String status) {
        try {
            Membership.MembershipStatus membershipStatus = Membership.MembershipStatus.valueOf(status.toUpperCase());
            return membershipRepository.countByStatus(membershipStatus);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status: " + status);
        }
    }
    
    public Membership updateMembership(String id, Membership membership) {
        if (!membershipRepository.existsById(id)) {
            throw new RuntimeException("Membership not found");
        }
        membership.setId(id);
        return membershipRepository.save(membership);
    }
    
    public void deleteMembership(String id) {
        if (!membershipRepository.existsById(id)) {
            throw new RuntimeException("Membership not found");
        }
        membershipRepository.deleteById(id);
    }
}
