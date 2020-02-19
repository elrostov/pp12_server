package org.example.dao;

import org.example.model.User;
import org.example.model.UserDto;

import java.util.List;

public interface UserDao {
    List<UserDto> getUsers();
    boolean createUser(User user);
    boolean updateUser(User user);
    void deleteUser(long id);
    User loadUserByUsername(String username);
}
