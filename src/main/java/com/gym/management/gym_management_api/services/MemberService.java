package com.gym.management.gym_management_api.services;

import com.gym.management.gym_management_api.models.Member;
import com.gym.management.gym_management_api.models.Trainer;
import com.gym.management.gym_management_api.repositories.MemberRepository;
import com.gym.management.gym_management_api.repositories.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private TrainerRepository trainerRepository;
    
    public Member createMember(Member member) {
        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return memberRepository.save(member);
    }
    
    public Page<Member> getAllMembers(int page, int size, String sort, String direction) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort));
        return memberRepository.findAll(pageable);
    }
    
    public Member getMemberById(String id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }
    
    public boolean checkEmailExists(String email) {
        return memberRepository.existsByEmail(email);
    }
    
    public Page<Member> getMembersByProvince(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return memberRepository.findByProvinceName(name, pageable);
    }
    
    public Page<Member> getMembersByDistrict(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return memberRepository.findByDistrictName(name, pageable);
    }
    
    public Page<Member> getMembersBySector(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return memberRepository.findBySectorName(name, pageable);
    }
    
    public Page<Member> getMembersByCell(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return memberRepository.findByCellName(name, pageable);
    }
    
    public Page<Member> getMembersByVillage(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return memberRepository.findByVillageName(name, pageable);
    }
    
    public void assignTrainer(String memberId, String trainerId) {
        Member member = getMemberById(memberId);
        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));
        
        member.getTrainers().add(trainer);
        memberRepository.save(member);
    }
    
    public Member updateMember(String id, Member member) {
        if (!memberRepository.existsById(id)) {
            throw new RuntimeException("Member not found");
        }
        member.setId(id);
        return memberRepository.save(member);
    }
    
    public void deleteMember(String id) {
        if (!memberRepository.existsById(id)) {
            throw new RuntimeException("Member not found");
        }
        memberRepository.deleteById(id);
    }
}
