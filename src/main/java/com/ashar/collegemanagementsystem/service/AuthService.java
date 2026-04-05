package com.ashar.collegemanagementsystem.service;

import com.ashar.collegemanagementsystem.dto.ChangePasswordDTO;
import com.ashar.collegemanagementsystem.dto.ForgotPasswordDTO;
import com.ashar.collegemanagementsystem.dto.ResetPasswordDTO;
import com.ashar.collegemanagementsystem.dto.request.*;
import com.ashar.collegemanagementsystem.dto.response.ApiResponse;

public interface AuthService {

    ApiResponse registerStudent(StudentRegisterDTO request);

    ApiResponse registerFaculty(FacultyRegisterDTO request);

    ApiResponse registerAdmin(AdminRegisterDTO request);

    ApiResponse loginStudent(LoginDTO request);

    ApiResponse loginFaculty(LoginDTO request);

    ApiResponse loginAdmin(LoginDTO request);

    ApiResponse forgotPassword(ForgotPasswordDTO request);

    ApiResponse resetPassword(ResetPasswordDTO request);

    ApiResponse changePassword(ChangePasswordDTO request);

}