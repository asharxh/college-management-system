package com.ashar.collegemanagementsystem.controller;

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
}