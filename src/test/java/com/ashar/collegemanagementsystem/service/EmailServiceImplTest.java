package com.ashar.collegemanagementsystem.service;

import com.ashar.collegemanagementsystem.service.impl.EmailServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;

class EmailServiceImplTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Test
    void testSendEmail() {
        assertDoesNotThrow(() -> {
            emailService.sendEmail("test@gmail.com", "Test Subject", "Test Body");
        });
    }
}