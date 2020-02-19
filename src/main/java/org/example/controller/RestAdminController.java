package org.example.controller;

import org.example.model.UserDto;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class RestAdminController {

    private final UserService userService;

    public RestAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> loadUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.loadUserByUsername(username), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        UserDto userDtoCreated = userService.createUser(userDto);
        return new ResponseEntity<>(userDtoCreated, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
        UserDto userDtoUpdated = userService.updateUser(userDto);
        return new ResponseEntity<>(userDtoUpdated, HttpStatus.OK);
    }
}
