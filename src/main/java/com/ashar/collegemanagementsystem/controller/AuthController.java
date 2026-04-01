package com.ashar.collegemanagementsystem.controller;

import com.ashar.collegemanagementsystem.dto.request.AdminRegisterDTO;
import com.ashar.collegemanagementsystem.dto.request.FacultyRegisterDTO;
import com.ashar.collegemanagementsystem.dto.request.StudentRegisterDTO;
import com.ashar.collegemanagementsystem.dto.response.ApiResponse;
import com.ashar.collegemanagementsystem.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/student/register")
    public ResponseEntity<ApiResponse> registerStudent(@Valid @RequestBody StudentRegisterDTO request) {

        ApiResponse response = authService.registerStudent(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/faculty/register")
    public ResponseEntity<ApiResponse> registerFaculty(@Valid @RequestBody FacultyRegisterDTO request) {

        ApiResponse response = authService.registerFaculty(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin/register")
    public ResponseEntity<ApiResponse> registerAdmin(@Valid @RequestBody AdminRegisterDTO request) {
        ApiResponse response = authService.registerAdmin(request);
        return ResponseEntity.ok(response);
    }
}