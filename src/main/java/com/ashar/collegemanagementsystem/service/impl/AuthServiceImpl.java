package com.ashar.collegemanagementsystem.service.impl;

import com.ashar.collegemanagementsystem.dto.request.AdminRegisterDTO;
import com.ashar.collegemanagementsystem.dto.request.FacultyRegisterDTO;
import com.ashar.collegemanagementsystem.dto.request.LoginDTO;
import com.ashar.collegemanagementsystem.dto.request.StudentRegisterDTO;
import com.ashar.collegemanagementsystem.dto.response.ApiResponse;
import com.ashar.collegemanagementsystem.entity.Admin;
import com.ashar.collegemanagementsystem.entity.FacultyPersonal;
import com.ashar.collegemanagementsystem.entity.Student;
import com.ashar.collegemanagementsystem.repository.AdminRepository;
import com.ashar.collegemanagementsystem.repository.FacultyRepository;
import com.ashar.collegemanagementsystem.repository.StudentRepository;
import com.ashar.collegemanagementsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final AdminRepository adminRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ApiResponse registerStudent(StudentRegisterDTO request) {

        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Student student = Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .branch(request.getBranch())
                .enrollmentYear(request.getEnrollmentYear())
                .createdAt(LocalDateTime.now())
                .build();

        studentRepository.save(student);

        return ApiResponse.builder()
                .success(true)
                .message("Student registered successfully")
                .data(student.getId())
                .build();
    }

    @Override
    public ApiResponse registerFaculty(FacultyRegisterDTO request) {

        if (facultyRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        FacultyPersonal faculty = FacultyPersonal.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .department(request.getDepartment())
                .designation(request.getDesignation())
                .createdAt(LocalDateTime.now())
                .build();

        facultyRepository.save(faculty);

        return ApiResponse.builder()
                .success(true)
                .message("Faculty registered successfully")
                .data(faculty.getId())
                .build();
    }

    @Override
    public ApiResponse registerAdmin(AdminRegisterDTO request) {

        if (adminRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Admin admin = Admin.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .createdAt(LocalDateTime.now())
                .build();

        adminRepository.save(admin);

        return ApiResponse.builder()
                .success(true)
                .message("Admin registered successfully")
                .data(admin.getId())
                .build();
    }

    @Override
    public ApiResponse loginStudent(LoginDTO request) {

        Student student = studentRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), student.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }

        Map<String, Object> data = Map.of(
                "userId", student.getId(),
                "name", student.getName(),
                "email", student.getEmail(),
                "role", "STUDENT"
        );

        return ApiResponse.builder()
                .success(true)
                .message("Login successful")
                .data(data)
                .build();
    }
}