package com.ashar.collegemanagementsystem.controller;

import com.ashar.collegemanagementsystem.dto.response.ApiResponse;
import com.ashar.collegemanagementsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final AuthService authService;

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse> getProfile() {
        return ResponseEntity.ok(authService.getStudentProfile());
    }
}