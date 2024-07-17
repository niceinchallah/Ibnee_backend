package com.test.test.service;

import com.test.test.entity.User;

public interface UserService {
    User register(User user);
    User login(String email, String password) throws Exception;
    User findByEmail(String email) throws Exception;
    void updatePassword(User user, String newPassword);
    boolean emailExists(String email);
}
