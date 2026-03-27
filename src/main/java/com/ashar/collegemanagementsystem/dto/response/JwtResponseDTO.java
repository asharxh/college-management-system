package com.ashar.collegemanagementsystem.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponseDTO {

    private String token;
    private String type;
    private Long userId;
    private String email;
    private String name;
    private String role;
}