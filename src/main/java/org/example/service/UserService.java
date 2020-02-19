package org.example.service;

import org.example.model.User;
import org.example.model.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto);
    void deleteUser(long id);
    User loadUserByUsername(String username);
}
