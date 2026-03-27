package com.ashar.collegemanagementsystem.dto.request;

import lombok.Data;

@Data
public class FacultyRegisterDTO {

    private String name;
    private String email;
    private String phone;
    private String password;
    private String department;
    private String designation;
}