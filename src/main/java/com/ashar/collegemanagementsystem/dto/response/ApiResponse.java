package com.ashar.collegemanagementsystem.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Standard API Response")
public class ApiResponse {

    @Schema(example = "true")
    private boolean success;

    @Schema(example = "Login successful")
    private String message;

    private Object data;
}