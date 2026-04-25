# College Management System (Spring Boot + JWT + PostgreSQL)

A complete backend project for managing college operations including students, faculty, courses, attendance, marks, and fee management with secure authentication using JWT.

---

## Tech Stack

- Java 17
- Spring Boot 3+
- Spring Security (JWT)
- Spring Data JPA / Hibernate
- PostgreSQL (Neon Cloud DB)
- Maven
- Lombok
- JavaMailSender
- JUnit 5 + Mockito
- Swagger / OpenAPI

---

## Features

### Student Features
- Student registration and login
- JWT authentication
- View profile
- Update profile
- Forgot & reset password via email
- Change password

### Faculty Features
- Faculty registration and login
- Role-based access control

### Admin Features
- Admin registration and login
- Role-based authorization

### System Features
- JWT-based authentication
- Role-based access (STUDENT, FACULTY, ADMIN)
- BCrypt password encryption
- Email notification system
- Stateless REST API architecture

---

## Database Entities

### Student
- id
- name
- email
- phone
- passwordHash
- branch
- semester
- enrollmentYear
- address
- city
- pincode

### Faculty
- id
- name
- email
- phone
- passwordHash
- department
- designation

### Admin
- id
- name
- email
- phone
- passwordHash
- role

### PasswordResetToken
- id
- email
- token
- expiryDate

---

## Security Features

- JWT authentication filter
- Stateless session management
- Role-based authorization
- BCrypt password hashing
- Protected endpoints using Spring Security
- Method-level security using @PreAuthorize

---

## API Endpoints

### Auth APIs

POST /api/auth/student/register  
POST /api/auth/student/login

POST /api/auth/faculty/register  
POST /api/auth/faculty/login

POST /api/auth/admin/register  
POST /api/auth/admin/login

---

### Password Management

POST /api/auth/forgot-password  
POST /api/auth/reset-password  
POST /api/auth/change-password

---

### Student APIs (Protected)

GET /api/student/profile  
PUT /api/student/profile

---

## Setup Instructions

### 1. Clone Repository
git clone https://github.com/asharxh/college-management-system.git  
cd college-management-system

### 2. Configure Environment Variables

DB_URL=your_database_url  
DB_USERNAME=your_db_username  
DB_PASSWORD=your_db_password

JWT_SECRET=your_secret_key

EMAIL_USERNAME=your_email  
EMAIL_PASSWORD=your_app_password

---

### 3. Build & Run

mvn clean install  
mvn spring-boot:run

---

## Testing

- JUnit 5 for unit testing
- Mockito for mocking dependencies
- MockMvc for controller testing

### Run Tests
mvn test

### Coverage Includes:
- AuthService (login, register, reset password)
- EmailService
- Controller layer APIs

---

## Postman Flow

### 1. Register Student
POST /api/auth/student/register

### 2. Login Student
POST /api/auth/student/login

Copy JWT token from response

---

### 3. Access Protected API

GET /api/student/profile

Header:
Authorization: Bearer <JWT_TOKEN>

---

### 4. Forgot Password
POST /api/auth/forgot-password

### 5. Reset Password
POST /api/auth/reset-password

---

## Deployment

### URL
https://college-management-system-r3wj.onrender.com

### Database
Neon PostgreSQL (Cloud Hosted)

---

## Future Enhancements

- Attendance management system
- Course & subject module
- File upload system (assignments/notes)
- Full Notifications system
- Analytics dashboard
- Mobile app integration

---

## Author

- Name: Ashar Arif
- Project: College Management System
- https://www.linkedin.com/in/ashararif/
