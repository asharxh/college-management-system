package com.ashar.collegemanagementsystem.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}