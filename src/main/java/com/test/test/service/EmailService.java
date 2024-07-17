package com.test.test.service;

public interface EmailService {
    void sendResetPasswordEmail(String to, String token);
}
