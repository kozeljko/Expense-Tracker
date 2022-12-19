package com.kozeljko.expensetracker.service;

import com.kozeljko.expensetracker.entity.User;
import com.kozeljko.expensetracker.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final String ADMIN_USERNAME = "demoadmin";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean isAdminInitialized() {
        return userRepository.findByUsername(ADMIN_USERNAME) != null;
    }

    public User createAdminUser() {
        var user = new User();
        user.setUsername(ADMIN_USERNAME);
        user.setPassword(passwordEncoder.encode(ADMIN_USERNAME));

        user = userRepository.save(user);
        return user;
    }
}
