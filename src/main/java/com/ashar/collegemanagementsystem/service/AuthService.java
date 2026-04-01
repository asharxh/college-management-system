package com.ashar.collegemanagementsystem.service;

import com.ashar.collegemanagementsystem.dto.request.StudentRegisterDTO;
import com.ashar.collegemanagementsystem.dto.response.ApiResponse;

public interface AuthService {

    ApiResponse registerStudent(StudentRegisterDTO request);
}