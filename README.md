<<<<<<< HEAD
# GYM AND FITNESS MEMBERSHIP MANAGEMENT SYSTEM

---

## PROJECT INFORMATION

| Field | Details |
|-------|---------|
| **Project Name** | Gym and Fitness Membership Management System |
| **Developer** | Enock Humure |
| **Group** | Web Technology |
| **Student ID** | [27394] |
| **Due Date** |13 March 2025 |
| **Course** | Web Technology  and internet |

---

## PROJECT OVERVIEW

### Problem Statement

Managing a gym requires handling multiple complex operations:
- **Member Management**: Tracking members across different locations with their details
- **Location Hierarchy**: Rwanda's administrative structure (Province → District → Sector → Cell → Village) needs proper organization
- **Membership Plans**: Different membership types (BASIC, PREMIUM, VIP) with expiration tracking
- **Payment Processing**: Recording payments with multiple methods (CASH, MOBILE_MONEY, BANK_CARD)
- **Trainer Assignment**: Assigning trainers to members for personalized fitness guidance
- **Attendance Tracking**: Recording member check-ins and check-outs
- **Fitness Classes**: Managing classes offered by trainers

### Solution

This Spring Boot REST API provides a complete solution for gym management with:

- **Location Hierarchy System** - Self-referencing 5-level administrative structure for Rwanda
- **Member Management** - Complete member profiles linked to locations with automatic hierarchy association
- **Membership Plans** - One-to-One relationship with members, status tracking (ACTIVE/EXPIRED)
- **Payment Processing** - Multiple payment methods with transaction tracking
- **Trainer Assignment** - Many-to-Many relationships between members and trainers
- **Attendance Tracking** - Check-in/check-out records for gym usage monitoring
- **Fitness Classes** - Class management with trainer assignment
- **Pagination & Filtering** - All endpoints support pagination and location-based filtering
- **Data Integrity** - Cascade delete ensures referential integrity
- **RESTful API** - Clean, intuitive endpoints for all operations

### Technologies Used

- **Backend**: Spring Boot 4.0.3
- **Database**: PostgreSQL 17
- **Language**: Java 21
- **Build Tool**: Maven
- **ORM**: Hibernate

---

## TABLE OF CONTENTS

1. [Entity Relationship Diagram](#entity-relationship-diagram)
2. [Location Hierarchy & Relationships](#location-hierarchy--relationships)
3. [Database Tables](#database-tables)
4. [Setup Instructions](#setup-instructions)
5. [CRUD Operations with Screenshots](#crud-operations-with-screenshots)
6. [Acknowledgements](#acknowledgements)

---

## ENTITY RELATIONSHIP DIAGRAM

The system has **8 tables** with the following relationships:

1. **locations** - Self-referencing hierarchy (Province → District → Sector → Cell → Village)
2. **members** - Gym members with location reference
3. **memberships** - Membership plans (One-to-One with members)
4. **trainers** - Gym trainers
5. **member_trainer** - Join table (Many-to-Many relationship)
6. **fitness_classes** - Classes offered by trainers
7. **attendances** - Check-in/check-out records
8. **payments** - Payment transactions

### ER Diagram Visual

![ER Diagram](EDR%20picture%20shows%20the%20project.png)

---

## LOCATION HIERARCHY & RELATIONSHIPS

### Location Self-Referencing Structure

The location hierarchy uses a self-referencing parent-child relationship to represent Rwanda's 5-level administrative structure:

**Hierarchy Levels:**
- **Level 1**: Province (no parent)
- **Level 2**: District (parent = Province)
- **Level 3**: Sector (parent = District)
- **Level 4**: Cell (parent = Sector)
- **Level 5**: Village (parent = Cell)

### Location Table in PostgreSQL

![Locations PostgreSQL Table](locations%20postgress.png)

### Location API Response

The Location API shows how locations reference each other through the parent_id field:

![Location Table API](location%20table%20api.png)

### Members by Location Hierarchy

Members are linked to locations at the Village level, and through the parent relationships, they are automatically associated with all administrative levels above them.

**Get Members by Province:**
![Get Members by Province](get%20all%20members%20from%20the%20province.png)

**Get Members by District:**
![Get Members by District](getting%20all%20members%20from%20the%20district.png)

**Get Members by Sector:**
![Get Members by Sector](gets%20all%20members%20from%20the%20sectors.png)

**Get Members by Cell:**
![Get Members by Cell](gets%20all%20members%20upto%20the%20cell.png)

**Get Members by Village:**
![Get Members by Village](getting%20members%20along%20to%20the%20village.png)

### Pagination Support for Location-Based Queries

The location-based member queries support pagination:

![Pagination in Province Query](pagination%20working%20in%20the%20province.png)

---

## DATABASE TABLES

### 1. Locations Table (Self-Referencing Hierarchy)

**Hierarchy Structure**:
- Province (Level 1)
- District (Level 2) - Parent: Province
- Sector (Level 3) - Parent: District
- Cell (Level 4) - Parent: Sector
- Village (Level 5) - Parent: Cell

---

### 2. Members Table

**Members in PostgreSQL:**
![Members PostgreSQL Table](members%20postgress.png)

**All Members API Response:**
![Get All Members](gets%20all%20members%20.png)

---

### 3. Memberships Table (One-to-One with Members)

**Plan Types**: BASIC, PREMIUM, VIP
**Status**: ACTIVE, EXPIRED

**Memberships in PostgreSQL:**
![Memberships PostgreSQL Table](members%20ship%20postgress%20table.png)

---

### 4. Trainers Table

**Trainers in PostgreSQL:**
![Trainers PostgreSQL Table](trainers%20postgress%20.png)

**All Trainers API Response:**
![Get All Trainers](gets%20all%20trainers.png)

**Trainers Pagination:**
![Trainers Pagination](pagnation%20of%20the%20trainers.png)

---

### 5. Member-Trainer Join Table (Many-to-Many)

**Member-Trainer Relationships in PostgreSQL:**
![Member-Trainer Relationships](members%20%20and%20their%20trainers%20postgress.png)

---

### 6. Fitness Classes Table

**Fitness Classes in PostgreSQL:**
![Fitness Classes PostgreSQL](fitness%20class%20posgress.png)

**Fitness Classes API Response:**
![Fitness Classes API](fitness%20class%20.png)

---

### 7. Attendances Table

**Attendance with Pagination:**
![Attendance with Pagination](attendance%20with%20pagination%20.png)

---

### 8. Payments Table

**Payment Methods**: CASH, MOBILE_MONEY, BANK_CARD

**Payments in PostgreSQL:**
![Payments PostgreSQL Table](payments%20%20postgress%20tables.png)

**All Payments API Response:**
![Get All Payments](gets%20all%20payments%20.png)

**Payments Pagination:**
![Payments Pagination](gets%20all%20payments%20pagnation.png)

---

## SETUP INSTRUCTIONS

### 1. Create Database
```sql
CREATE DATABASE gym_fitness_management;
```

### 2. Configure application.properties
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gym_fitness_management
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD
server.port=8081
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
```

### 3. Run Application
```bash
mvn clean install
mvn spring-boot:run
```

Application runs at: http://localhost:8081

---

## CRUD OPERATIONS WITH SCREENSHOTS

### Complete CRUD Cycle Example: Hirwa Enock

This section demonstrates all CRUD operations (Create, Read, Update, Delete) using the member "Hirwa Enock" as an example.

---

### STEP 1: CREATE - Create Member "Hirwa Enock"

**Postman Request:**
![Create Member Postman](crud%20operation%20creation%20of%20members%20in%20the%20.png)

**Description**: 
- POST request to /api/members
- Provide firstName, lastName, email, phone, and location ID
- Returns 201 Created with member details

---

### STEP 2: READ - Get Member by ID

**Postman Request:**
![Read Member by ID](crud%20operation%20read%20a%20member%20with%20the%20id%20.png)

**Description**:
- GET request to /api/members/{id}
- Returns 200 OK with complete member information
- Shows member's location, membership, and trainers

---

### STEP 3: UPDATE - Update Member Phone Number

**Postman Request:**
![Update Member](crud%20operation%20update%20hirwa%20to%20enock.png)

**Description**:
- PUT request to /api/members/{id}
- Update any member fields (firstName, lastName, email, phone, location)
- Returns 200 OK with updated member data

---

### STEP 4: CREATE - Create Membership for Member

**Postman Request:**
![Create Membership](crud%20operation%20create%20membership%20of%20hirwa%20.png)

**Description**:
- POST request to /api/memberships
- Link membership to member using member ID
- Set plan type (BASIC, PREMIUM, VIP), start date, end date, and status
- Returns 201 Created with membership details

**Membership API Response:**
![Membership Creation Response](membership%20creation.png)

---

### STEP 5: CREATE - Create Payment for Membership

**Postman Request:**
![Create Payment](crud%20operation%20create%20a%20payment%20methods%20.png)

**Description**:
- POST request to /api/payments
- Link payment to membership using membership ID
- Specify amount, payment date, payment method (CASH, MOBILE_MONEY, BANK_CARD), and transaction reference
- Returns 201 Created with payment details

---

### STEP 6: CREATE - Create Attendance Record

**Postman Request:**
![Create Attendance](crud%20operation%20create%20attendance%20for%20new%20member.png)

**Description**:
- POST request to /api/attendances
- Link attendance to member using member ID
- Provide check-in time and check-out time
- Returns 201 Created with attendance details

---

### STEP 7: READ - Get Member's Membership

**Postman Request:**
![Read Membership](crud%20operation%20for%20the%20membership%20with%20id%20.png)

**Description**:
- GET request to /api/memberships/member/{memberId}
- Returns 200 OK with membership information for the specific member

---

### STEP 8: READ - Get Member's Payments

**Postman Request:**
![Read Payments](crud%20operation%20read%20payment%20for%20new%20members.png)

**Description**:
- GET request to /api/payments/membership/{membershipId}
- Returns 200 OK with paginated list of payments for the membership

---

### STEP 9: READ - Get Member's Attendance Records

**Postman Request:**
![Read Attendance](crud%20operationread%20the%20attendance%20of%20new%20member%20.png)

**Description**:
- GET request to /api/attendances/member/{memberId}
- Returns 200 OK with paginated list of attendance records

---

### STEP 10: UPDATE - Update Membership Status

**Postman Request:**
![Update Membership](crud%20operation%20of%20membership%20.png)

**Description**:
- PUT request to /api/memberships/{id}
- Update membership status from ACTIVE to EXPIRED or vice versa
- Returns 200 OK with updated membership data

---

### STEP 11: DELETE - Delete Payment Record

**Postman Request:**
![Delete Payment](delete%20member%20on%20the%20attendance.png)

**Description**:
- DELETE request to /api/payments/{id}
- Removes payment record from database
- Returns 200 OK with success message

---

### STEP 12: DELETE - Delete Attendance Record

**Postman Request:**
![Delete Attendance](crud%20operation%20delete%20member%20in%20attendance%20.png)

**Description**:
- DELETE request to /api/attendances/{id}
- Removes attendance record from database
- Returns 200 OK with success message

---

### STEP 13: DELETE - Delete Membership

**Postman Request:**
![Delete Membership](crud%20operation%20delete%20members%20.png)

**Description**:
- DELETE request to /api/memberships/{id}
- Removes membership record (cascade deletes related payments)
- Returns 200 OK with success message

---

### STEP 14: DELETE - Delete Member

**Postman Request:**
![Delete Member](crud%20operation%20delete%20members%20.png)

**Description**:
- DELETE request to /api/members/{id}
- Removes member record (cascade deletes related memberships, payments, attendances)
- Returns 200 OK with success message

---

## ACKNOWLEDGEMENTS

This project was developed as part of the Web Technology mid term exam course. Special thanks to:

- **Eng. Patrick Fils** - Lecturer of Web Technology and Internet for teaching Spring Boot and guiding us through this project
- **Spring Boot Framework** - For providing a robust REST API development platform
- **PostgreSQL** - For reliable and scalable database management
- **Hibernate ORM** - For seamless object-relational mapping
- **Maven** - For efficient project build and dependency management
- **Postman** - For API testing and documentation

The project demonstrates practical implementation of:
- RESTful API design principles
- Database normalization and relationships
- Self-referencing hierarchical data structures
- Cascade delete for data integrity
- Pagination and filtering mechanisms
- Spring Boot best practices

---

Project Completed: 13 March 2025
=======
# Midterm_27394_E
>>>>>>> 823c9c6eecc29ed80cb1c9b6b820f9f15259be5f
