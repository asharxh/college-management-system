package com.ashar.collegemanagementsystem.controller;

import com.ashar.collegemanagementsystem.dto.request.StudentRegisterDTO;
import com.ashar.collegemanagementsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/student/register")
    public ResponseEntity<String> registerStudent(@RequestBody StudentRegisterDTO request) {

        String response = authService.registerStudent(request);
        return ResponseEntity.ok(response);
    }
}