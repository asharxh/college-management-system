package com.ashar.collegemanagementsystem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Login request payload")
public class LoginDTO {

    @Schema(example = "rahul@college.edu")
    private String email;

    @Schema(example = "SecurePass@123")
    private String password;
}