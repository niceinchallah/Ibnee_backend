package com.test.test.controller;

import com.test.test.entity.User;
import com.test.test.service.EmailService;
import com.test.test.service.UserService;
import com.test.test.service.impl.TokenServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenServiceImpl tokenService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/check-email")
    public ResponseEntity<?> checkEmail(@RequestBody EmailRequest emailRequest) {
        boolean exists = userService.emailExists(emailRequest.getEmail());
        return ResponseEntity.ok(Collections.singletonMap("exists", exists));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.emailExists(user.getEmail())) {
            return ResponseEntity.status(400).body("Il y a déjà un compte avec ce email");
        }
        User registeredUser = userService.register(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
            String token = tokenService.generateToken(user.getEmail());
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        try {
            User user = userService.findByEmail(request.getEmail());
            String token = tokenService.generateToken(user.getEmail());
            emailService.sendResetPasswordEmail(user.getEmail(), token);
            return ResponseEntity.ok("Reset password email sent");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Email not found");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            Claims claims = tokenService.extractClaims(request.getToken());
            User user = userService.findByEmail(claims.getSubject());
            userService.updatePassword(user, request.getNewPassword());
            return ResponseEntity.ok("Password updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Invalid token");
        }
    }

    static class EmailRequest {
        private String email;

        // Getters and Setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    static class LoginRequest {
        private String email;
        private String password;

        // Getters and Setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    static class LoginResponse {
        private String token;

        // Constructor, Getters and Setters
        public LoginResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    static class ForgotPasswordRequest {
        private String email;

        // Getters and Setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    static class ResetPasswordRequest {
        private String token;
        private String newPassword;

        // Getters and Setters
        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }
}
