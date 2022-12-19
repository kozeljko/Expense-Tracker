package com.kozeljko.expensetracker.service;

import com.kozeljko.expensetracker.dto.UserDTO;
import com.kozeljko.expensetracker.entity.User;
import com.kozeljko.expensetracker.mapper.UserMapper;
import com.kozeljko.expensetracker.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final String ADMIN_USERNAME = "demoadmin";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
        UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::userToUserDTO).orElse(null);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::userToUserDTO).toList();
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
