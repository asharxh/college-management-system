package com.ashar.collegemanagementsystem.service;

import com.ashar.collegemanagementsystem.dto.request.LoginDTO;
import com.ashar.collegemanagementsystem.entity.Student;
import com.ashar.collegemanagementsystem.repository.*;
import com.ashar.collegemanagementsystem.security.JwtUtil;
import com.ashar.collegemanagementsystem.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AuthServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private FacultyRepository facultyRepository;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginStudentSuccess() {

        Student student = new Student();
        student.setId(1L);
        student.setEmail("test@student.com");
        student.setName("Test User");

        String encodedPassword = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder()
                .encode("password");

        student.setPasswordHash(encodedPassword);

        when(studentRepository.findByEmail("test@student.com"))
                .thenReturn(Optional.of(student));

        when(jwtUtil.generateToken(anyLong(), anyString(), anyString()))
                .thenReturn("dummy-jwt-token");

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("test@student.com");
        loginDTO.setPassword("password");

        var response = authService.loginStudent(loginDTO);

        assertTrue(response.isSuccess());
        assertEquals("Login successful", response.getMessage());
        assertNotNull(response.getData());
    }

    @Test
    void testLoginStudentWrongPassword() {

        Student student = new Student();
        student.setEmail("test@student.com");
        student.setPasswordHash(
                new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder()
                        .encode("correctPassword")
        );

        when(studentRepository.findByEmail("test@student.com"))
                .thenReturn(Optional.of(student));

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("test@student.com");
        loginDTO.setPassword("wrongPassword");

        assertThrows(RuntimeException.class, () -> {
            authService.loginStudent(loginDTO);
        });
    }

    @Test
    void testLoginStudentUserNotFound() {

        when(studentRepository.findByEmail("notfound@student.com"))
                .thenReturn(Optional.empty());

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("notfound@student.com");
        loginDTO.setPassword("password");

        assertThrows(RuntimeException.class, () -> {
            authService.loginStudent(loginDTO);
        });
    }
}