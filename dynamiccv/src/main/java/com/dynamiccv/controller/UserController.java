package com.dynamiccv.controller;

import com.dynamiccv.dto.UserDTO;
import com.dynamiccv.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get user by ID", description = "Retrieve a single user by their ID")
    @GetMapping("/{id}")
    public UserDTO getUserById(
            @Parameter(description = "ID of the user to retrieve") @PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "Create a new user", description = "Create a new user with all required details")
    @PostMapping
    public UserDTO createUser(
            @Parameter(description = "User data to create") @Valid @RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @Operation(summary = "Get all users", description = "Retrieve a list of all users")
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Update a user", description = "Update an existing user by ID")
    @PutMapping("/{id}")
    public UserDTO updateUser(
            @Parameter(description = "ID of the user to update") @PathVariable Long id,
            @Parameter(description = "Updated user data") @Valid @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @Operation(summary = "Delete a user", description = "Delete a user by ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(
            @Parameter(description = "ID of the user to delete") @PathVariable Long id) {
        userService.deleteUser(id);
    }
}
