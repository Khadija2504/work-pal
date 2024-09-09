package com.workpal.service;

import com.workpal.model.User;
import com.workpal.repository.UserRepository;

import java.sql.SQLException;
import java.util.Optional;

public class AuthService {
    private UserRepository userRepository = new UserRepository();

    public boolean register(String name, String email, String password, String address, String phone, String role) throws SQLException {
        User existingUser = userRepository.getUserByEmail(email);
        if (existingUser != null) {
            return false;
        }
        userRepository.saveUser(new User(0, name, email, password, address, phone, role));
        return true;
    }

    public Optional<User> login(String email, String password) throws SQLException {
        User user = userRepository.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
