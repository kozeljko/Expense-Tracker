package com.kozeljko.expensetracker.controller;

import com.kozeljko.expensetracker.dto.UserDTO;
import com.kozeljko.expensetracker.entity.User;
import com.kozeljko.expensetracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * TODO This should be probably moved to a bean initializing the user when application first starts up.
     *
     * @return created admin
     */
    @PostMapping(path = "/init-admin")
    public ResponseEntity<User> initializeAdmin() {
        if (userService.isAdminInitialized()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(userService.createAdminUser());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        var userDTO = userService.getUserById(id);
        if (userDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
