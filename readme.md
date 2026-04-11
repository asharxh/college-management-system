# College Management System - Backend API

A complete backend project for managing college operations including students, faculty, courses, attendance, marks, and fee management with secure authentication using JWT.

---

## Tech Stack

- Java 17+
- Spring Boot 3.2+
- Spring Security + JWT
- Spring Data JPA (Hibernate)
- PostgreSQL
- Maven
- Swagger / OpenAPI
- Postman (API Testing)

---

## Features

### Authentication & Security
- Student / Faculty / Admin Registration
- JWT-based Login System
- Role-Based Access Control (RBAC)
- Password Encryption (BCrypt)
- Forgot Password (Email Reset Link)
- Change Password

### Student Module
- View Profile
- Update Profile
- Secure JWT Access

### Faculty Module
- Faculty Registration/Login
- Role-based access control

### Admin Module
- Admin Registration/Login
- Manage system-level access

### Email Service
- Forgot Password Email Integration
- Secure token-based reset link

---

## Database Entities

- Student
- FacultyPersonal
- Admin
- Subject
- Course
- Enrollment
- Attendance
- Marks
- FeeStructure
- FeePayment
- PasswordResetToken

---

## Security Features

- JWT Authentication Filter
- Role-based authorization:
    - STUDENT
    - FACULTY
    - ADMIN / SUPERADMIN
- Secure API endpoints
- Password hashing using BCrypt

---

## API Documentation (Swagger)

http://localhost:8080/swagger-ui/index.html

---

## Main API Endpoints

### Auth APIs

POST /api/auth/student/register  
POST /api/auth/student/login  
POST /api/auth/faculty/register  
POST /api/auth/faculty/login  
POST /api/auth/admin/register  
POST /api/auth/admin/login

---

### Password APIs

POST /api/auth/forgot-password  
POST /api/auth/reset-password  
POST /api/auth/change-password

---

### Student APIs

GET  /api/student/profile  
PUT  /api/student/profile

---

## Setup Instructions

### Clone Repository

git clone https://github.com/your-username/college-management-system.git

---

### Configure Database

Update application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/college_db  
spring.datasource.username=your_username  
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update  
spring.jpa.show-sql=true

---

### Run Application

mvn clean install  
mvn spring-boot:run

---

### Access Application

Backend API: http://localhost:8080  
Swagger UI: http://localhost:8080/swagger-ui/index.html

---

## Testing

### Unit Testing
- JUnit + Mockito used
- Service layer coverage: 70%+

### API Testing
- Postman Collection included
- Automated test scripts enabled

---

## Postman Flow

1. Register Student
2. Login Student (JWT generated)
3. Use Token in Authorization Header
4. Access Protected APIs

---

## Deployment (i am Working on it)

- Backend: Deployment
- Database: Cloud (upcoming)
- Frontend: React (Upcoming)

---

## Future Enhancements

- Attendance system
- Marks management
- Fee payment integration
- Notification system
- React frontend dashboard

---

## Author

- Name: Ashar Arif
- Project: College Management System
- https://www.linkedin.com/in/ashararif/