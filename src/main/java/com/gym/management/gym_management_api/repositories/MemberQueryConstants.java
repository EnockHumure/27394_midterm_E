package com.gym.management.gym_management_api.repositories;

public class MemberQueryConstants {
    
    public static final String PROVINCE_QUERY =
        "WITH RECURSIVE location_tree AS " +
        "(SELECT id, name FROM locations WHERE name = :provinceName " +
        "UNION ALL " +
        "SELECT l.id, l.name FROM locations l " +
        "INNER JOIN location_tree lt ON l.parent_id = lt.id) " +
        "SELECT DISTINCT m.* FROM members m " +
        "INNER JOIN location_tree lt ON m.location_id = lt.id";
    
    public static final String PROVINCE_COUNT_QUERY =
        "WITH RECURSIVE location_tree AS " +
        "(SELECT id, name FROM locations WHERE name = :provinceName " +
        "UNION ALL " +
        "SELECT l.id, l.name FROM locations l " +
        "INNER JOIN location_tree lt ON l.parent_id = lt.id) " +
        "SELECT COUNT(DISTINCT m.id) FROM members m " +
        "INNER JOIN location_tree lt ON m.location_id = lt.id";
    
    public static final String DISTRICT_QUERY =
        "WITH RECURSIVE location_tree AS " +
        "(SELECT id, name FROM locations WHERE name = :districtName " +
        "UNION ALL " +
        "SELECT l.id, l.name FROM locations l " +
        "INNER JOIN location_tree lt ON l.parent_id = lt.id) " +
        "SELECT DISTINCT m.* FROM members m " +
        "INNER JOIN location_tree lt ON m.location_id = lt.id";
    
    public static final String DISTRICT_COUNT_QUERY =
        "WITH RECURSIVE location_tree AS " +
        "(SELECT id, name FROM locations WHERE name = :districtName " +
        "UNION ALL " +
        "SELECT l.id, l.name FROM locations l " +
        "INNER JOIN location_tree lt ON l.parent_id = lt.id) " +
        "SELECT COUNT(DISTINCT m.id) FROM members m " +
        "INNER JOIN location_tree lt ON m.location_id = lt.id";
    
    public static final String SECTOR_QUERY =
        "WITH RECURSIVE location_tree AS " +
        "(SELECT id, name FROM locations WHERE name = :sectorName " +
        "UNION ALL " +
        "SELECT l.id, l.name FROM locations l " +
        "INNER JOIN location_tree lt ON l.parent_id = lt.id) " +
        "SELECT DISTINCT m.* FROM members m " +
        "INNER JOIN location_tree lt ON m.location_id = lt.id";
    
    public static final String SECTOR_COUNT_QUERY =
        "WITH RECURSIVE location_tree AS " +
        "(SELECT id, name FROM locations WHERE name = :sectorName " +
        "UNION ALL " +
        "SELECT l.id, l.name FROM locations l " +
        "INNER JOIN location_tree lt ON l.parent_id = lt.id) " +
        "SELECT COUNT(DISTINCT m.id) FROM members m " +
        "INNER JOIN location_tree lt ON m.location_id = lt.id";
    
    public static final String CELL_QUERY =
        "WITH RECURSIVE location_tree AS " +
        "(SELECT id, name FROM locations WHERE name = :cellName " +
        "UNION ALL " +
        "SELECT l.id, l.name FROM locations l " +
        "INNER JOIN location_tree lt ON l.parent_id = lt.id) " +
        "SELECT DISTINCT m.* FROM members m " +
        "INNER JOIN location_tree lt ON m.location_id = lt.id";
    
    public static final String CELL_COUNT_QUERY =
        "WITH RECURSIVE location_tree AS " +
        "(SELECT id, name FROM locations WHERE name = :cellName " +
        "UNION ALL " +
        "SELECT l.id, l.name FROM locations l " +
        "INNER JOIN location_tree lt ON l.parent_id = lt.id) " +
        "SELECT COUNT(DISTINCT m.id) FROM members m " +
        "INNER JOIN location_tree lt ON m.location_id = lt.id";
    
    public static final String VILLAGE_QUERY =
        "WITH RECURSIVE location_tree AS " +
        "(SELECT id, name, parent_id FROM locations WHERE name = :villageName " +
        "UNION ALL " +
        "SELECT l.id, l.name, l.parent_id FROM locations l " +
        "INNER JOIN location_tree lt ON l.id = lt.parent_id) " +
        "SELECT DISTINCT m.* FROM members m " +
        "INNER JOIN location_tree lt ON m.location_id = lt.id";
    
    public static final String VILLAGE_COUNT_QUERY =
        "WITH RECURSIVE location_tree AS " +
        "(SELECT id, name, parent_id FROM locations WHERE name = :villageName " +
        "UNION ALL " +
        "SELECT l.id, l.name, l.parent_id FROM locations l " +
        "INNER JOIN location_tree lt ON l.id = lt.parent_id) " +
        "SELECT COUNT(DISTINCT m.id) FROM members m " +
        "INNER JOIN location_tree lt ON m.location_id = lt.id";
}
