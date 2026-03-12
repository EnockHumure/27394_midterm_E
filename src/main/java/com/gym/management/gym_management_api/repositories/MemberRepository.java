package com.gym.management.gym_management_api.repositories;

import com.gym.management.gym_management_api.models.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    
    boolean existsByEmail(String email);
    
    @Query(
        value = MemberQueryConstants.PROVINCE_QUERY,
        countQuery = MemberQueryConstants.PROVINCE_COUNT_QUERY,
        nativeQuery = true
    )
    Page<Member> findByProvinceName(
        @Param("provinceName") String provinceName,
        Pageable pageable
    );
    
    @Query(
        value = MemberQueryConstants.DISTRICT_QUERY,
        countQuery = MemberQueryConstants.DISTRICT_COUNT_QUERY,
        nativeQuery = true
    )
    Page<Member> findByDistrictName(
        @Param("districtName") String districtName,
        Pageable pageable
    );
    
    @Query(
        value = MemberQueryConstants.SECTOR_QUERY,
        countQuery = MemberQueryConstants.SECTOR_COUNT_QUERY,
        nativeQuery = true
    )
    Page<Member> findBySectorName(
        @Param("sectorName") String sectorName,
        Pageable pageable
    );
    
    @Query(
        value = MemberQueryConstants.CELL_QUERY,
        countQuery = MemberQueryConstants.CELL_COUNT_QUERY,
        nativeQuery = true
    )
    Page<Member> findByCellName(
        @Param("cellName") String cellName,
        Pageable pageable
    );
    
    @Query(
        value = MemberQueryConstants.VILLAGE_QUERY,
        countQuery = MemberQueryConstants.VILLAGE_COUNT_QUERY,
        nativeQuery = true
    )
    Page<Member> findByVillageName(
        @Param("villageName") String villageName,
        Pageable pageable
    );
    
    Page<Member> findAll(Pageable pageable);
}
