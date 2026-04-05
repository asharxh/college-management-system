package com.ashar.collegemanagementsystem.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class ForgotPasswordDTO {

    @Email
    private String email;
}