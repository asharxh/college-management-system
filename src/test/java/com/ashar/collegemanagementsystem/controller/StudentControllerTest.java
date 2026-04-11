package com.ashar.collegemanagementsystem.controller;

import com.ashar.collegemanagementsystem.dto.UpdateStudentDTO;
import com.ashar.collegemanagementsystem.dto.response.ApiResponse;
import com.ashar.collegemanagementsystem.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetProfile() throws Exception {

        ApiResponse response = ApiResponse.builder()
                .success(true)
                .message("Profile fetched successfully")
                .build();

        Mockito.when(authService.getStudentProfile())
                .thenReturn(response);

        mockMvc.perform(get("/api/student/profile"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void testUpdateProfile() throws Exception {

        UpdateStudentDTO request = new UpdateStudentDTO();
        request.setPhone("9999999999");

        ApiResponse response = ApiResponse.builder()
                .success(true)
                .message("Profile updated successfully")
                .build();

        Mockito.when(authService.updateStudentProfile(Mockito.any()))
                .thenReturn(response);

        mockMvc.perform(put("/api/student/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Profile updated successfully"));
    }
}