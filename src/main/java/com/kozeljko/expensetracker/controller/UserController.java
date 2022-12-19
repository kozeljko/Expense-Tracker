package com.kozeljko.expensetracker.controller;

import com.kozeljko.expensetracker.entity.User;
import com.kozeljko.expensetracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping(path = "/init-admin")
    public ResponseEntity<User> initializeAdmin() {
        if (userService.isAdminInitialized()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(userService.createAdminUser());
    }

}
