package com.gym.management.gym_management_api.repositories;

import com.gym.management.gym_management_api.models.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    
    boolean existsByEmail(String email);
    
    @Query(value = "WITH RECURSIVE location_tree AS (" +
            "SELECT id, name FROM locations WHERE name = :provinceName " +
            "UNION ALL " +
            "SELECT l.id, l.name FROM locations l " +
            "INNER JOIN location_tree lt ON l.parent_id = lt.id" +
            ") " +
            "SELECT DISTINCT m.* FROM members m " +
            "INNER JOIN location_tree lt ON m.location_id = lt.id", nativeQuery = true, countQuery = "WITH RECURSIVE location_tree AS (" +
            "SELECT id, name FROM locations WHERE name = :provinceName " +
            "UNION ALL " +
            "SELECT l.id, l.name FROM locations l " +
            "INNER JOIN location_tree lt ON l.parent_id = lt.id" +
            ") " +
            "SELECT COUNT(DISTINCT m.id) FROM members m " +
            "INNER JOIN location_tree lt ON m.location_id = lt.id")
    Page<Member> findByProvinceName(@Param("provinceName") String provinceName, Pageable pageable);
    
    @Query(value = "WITH RECURSIVE location_tree AS (" +
            "SELECT id, name FROM locations WHERE name = :districtName " +
            "UNION ALL " +
            "SELECT l.id, l.name FROM locations l " +
            "INNER JOIN location_tree lt ON l.parent_id = lt.id" +
            ") " +
            "SELECT DISTINCT m.* FROM members m " +
            "INNER JOIN location_tree lt ON m.location_id = lt.id", nativeQuery = true, countQuery = "WITH RECURSIVE location_tree AS (" +
            "SELECT id, name FROM locations WHERE name = :districtName " +
            "UNION ALL " +
            "SELECT l.id, l.name FROM locations l " +
            "INNER JOIN location_tree lt ON l.parent_id = lt.id" +
            ") " +
            "SELECT COUNT(DISTINCT m.id) FROM members m " +
            "INNER JOIN location_tree lt ON m.location_id = lt.id")
    Page<Member> findByDistrictName(@Param("districtName") String districtName, Pageable pageable);
    
    @Query(value = "WITH RECURSIVE location_tree AS (" +
            "SELECT id, name FROM locations WHERE name = :sectorName " +
            "UNION ALL " +
            "SELECT l.id, l.name FROM locations l " +
            "INNER JOIN location_tree lt ON l.parent_id = lt.id" +
            ") " +
            "SELECT DISTINCT m.* FROM members m " +
            "INNER JOIN location_tree lt ON m.location_id = lt.id", nativeQuery = true, countQuery = "WITH RECURSIVE location_tree AS (" +
            "SELECT id, name FROM locations WHERE name = :sectorName " +
            "UNION ALL " +
            "SELECT l.id, l.name FROM locations l " +
            "INNER JOIN location_tree lt ON l.parent_id = lt.id" +
            ") " +
            "SELECT COUNT(DISTINCT m.id) FROM members m " +
            "INNER JOIN location_tree lt ON m.location_id = lt.id")
    Page<Member> findBySectorName(@Param("sectorName") String sectorName, Pageable pageable);
    
    @Query(value = "WITH RECURSIVE location_tree AS (" +
            "SELECT id, name FROM locations WHERE name = :cellName " +
            "UNION ALL " +
            "SELECT l.id, l.name FROM locations l " +
            "INNER JOIN location_tree lt ON l.parent_id = lt.id" +
            ") " +
            "SELECT DISTINCT m.* FROM members m " +
            "INNER JOIN location_tree lt ON m.location_id = lt.id", nativeQuery = true, countQuery = "WITH RECURSIVE location_tree AS (" +
            "SELECT id, name FROM locations WHERE name = :cellName " +
            "UNION ALL " +
            "SELECT l.id, l.name FROM locations l " +
            "INNER JOIN location_tree lt ON l.parent_id = lt.id" +
            ") " +
            "SELECT COUNT(DISTINCT m.id) FROM members m " +
            "INNER JOIN location_tree lt ON m.location_id = lt.id")
    Page<Member> findByCellName(@Param("cellName") String cellName, Pageable pageable);
    
    @Query(value = "WITH RECURSIVE location_tree AS (" +
            "SELECT id, name, parent_id FROM locations WHERE name = :villageName " +
            "UNION ALL " +
            "SELECT l.id, l.name, l.parent_id FROM locations l " +
            "INNER JOIN location_tree lt ON l.id = lt.parent_id" +
            ") " +
            "SELECT DISTINCT m.* FROM members m " +
            "INNER JOIN location_tree lt ON m.location_id = lt.id", nativeQuery = true, countQuery = "WITH RECURSIVE location_tree AS (" +
            "SELECT id, name, parent_id FROM locations WHERE name = :villageName " +
            "UNION ALL " +
            "SELECT l.id, l.name, l.parent_id FROM locations l " +
            "INNER JOIN location_tree lt ON l.id = lt.parent_id" +
            ") " +
            "SELECT COUNT(DISTINCT m.id) FROM members m " +
            "INNER JOIN location_tree lt ON m.location_id = lt.id")
    Page<Member> findByVillageName(@Param("villageName") String villageName, Pageable pageable);
    
    Page<Member> findAll(Pageable pageable);
}
