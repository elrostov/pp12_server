package org.example.service;

import org.example.dao.UserDao;
import org.example.model.User;
import org.example.model.UserDto;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final RoleService roleService;

    public UserServiceImpl(UserDao userDao, RoleService roleService) {
        this.userDao = userDao;
        this.roleService = roleService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.loadUserByUsername(username);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = new User(userDto);
        user.setRoles(Arrays.stream(userDto.getRoles()).map(roleService::getRole).collect(Collectors.toSet()));
        userDao.createUser(user);
        user = userDao.loadUserByUsername(user.getUsername());
        userDto.setId(user.getId());
        userDto.setPassword("");
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User userFromDB = userDao.loadUserByUsername(userDto.getUsername());
        if (userDto.getPassword().isEmpty()) {
            userDto.setPassword(userFromDB.getPassword());
        }
        User user = new User(userDto);
        user.setRoles(Arrays.stream(userDto.getRoles()).map(roleService::getRole).collect(Collectors.toSet()));
        userDao.updateUser(user);
        userDto.setPassword("");
        return userDto;
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }
}
