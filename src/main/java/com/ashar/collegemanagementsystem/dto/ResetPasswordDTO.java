package com.ashar.collegemanagementsystem.dto;

import lombok.Data;

@Data
public class ResetPasswordDTO {

    private String token;
    private String newPassword;
}