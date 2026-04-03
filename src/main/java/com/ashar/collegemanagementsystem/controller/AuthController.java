package com.ashar.collegemanagementsystem.controller;

import com.ashar.collegemanagementsystem.dto.request.AdminRegisterDTO;
import com.ashar.collegemanagementsystem.dto.request.FacultyRegisterDTO;
import com.ashar.collegemanagementsystem.dto.request.LoginDTO;
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

    @PostMapping("/student/login")
    public ResponseEntity<ApiResponse> loginStudent(@RequestBody LoginDTO request) {
        ApiResponse response = authService.loginStudent(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/faculty/login")
    public ResponseEntity<ApiResponse> loginFaculty(@RequestBody LoginDTO request) {
        ApiResponse response = authService.loginFaculty(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<ApiResponse> loginAdmin(@RequestBody LoginDTO request) {
        ApiResponse response = authService.loginAdmin(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/test")
    public String studentTest() {
        return "Student Access Granted";
    }

    @GetMapping("/faculty/test")
    public String facultyTest() {
        return "Faculty Access Granted";
    }

    @GetMapping("/admin/test")
    public String adminTest() {
        return "Admin Access Granted";
    }
}