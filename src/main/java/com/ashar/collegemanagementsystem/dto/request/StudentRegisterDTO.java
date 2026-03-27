package com.ashar.collegemanagementsystem.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentRegisterDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone is required")
    private String phone;

    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotBlank(message = "Branch is required")
    private String branch;

    @NotNull(message = "Enrollment year is required")
    private Integer enrollmentYear;
}