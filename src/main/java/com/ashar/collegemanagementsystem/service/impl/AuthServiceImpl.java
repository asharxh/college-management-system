package com.ashar.collegemanagementsystem.service.impl;

import com.ashar.collegemanagementsystem.dto.request.StudentRegisterDTO;
import com.ashar.collegemanagementsystem.dto.response.ApiResponse;
import com.ashar.collegemanagementsystem.entity.Student;
import com.ashar.collegemanagementsystem.repository.StudentRepository;
import com.ashar.collegemanagementsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final StudentRepository studentRepository;

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
}