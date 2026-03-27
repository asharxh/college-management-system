package com.ashar.collegemanagementsystem.dto.request;

import lombok.Data;

@Data
public class AdminRegisterDTO {

    private String name;
    private String email;
    private String phone;
    private String password;
    private String role;
}