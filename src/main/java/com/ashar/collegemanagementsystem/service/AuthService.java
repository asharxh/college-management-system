package com.ashar.collegemanagementsystem.service;

import com.ashar.collegemanagementsystem.dto.request.StudentRegisterDTO;

public interface AuthService {

    String registerStudent(StudentRegisterDTO request);
}