package com.ashar.collegemanagementsystem.controller;

import com.ashar.collegemanagementsystem.dto.UpdateStudentDTO;
import com.ashar.collegemanagementsystem.dto.response.ApiResponse;
import com.ashar.collegemanagementsystem.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
@Tag(name = "Student APIs", description = "Operations related to students")
public class StudentController {

    private final AuthService authService;

    @Operation(summary = "Get Student Profile", description = "Fetch logged-in student details using JWT")
    @GetMapping("/profile")
    public ResponseEntity<ApiResponse> getProfile() {
        return ResponseEntity.ok(authService.getStudentProfile());
    }

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse> updateProfile(
            @RequestBody UpdateStudentDTO request) {
        return ResponseEntity.ok(authService.updateStudentProfile(request));
    }
}