package com.jms.boilerplate.userservice.service;

import com.jms.boilerplate.userservice.dao.UserDao;
import com.jms.boilerplate.userservice.dto.UserDto;
import com.jms.boilerplate.userservice.exception.UserAlreadyExistsException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<UserDto> getUsers() {
        return this.userDao.findAll();
    }

    public Optional<UserDto> getUser(String id) {
        return this.userDao.findById(id);
    }

    public UserDto createUser(UserDto userDto) {
        // Check if email already exists to handle
        // ERROR: duplicate key value violates unique constraint "" Detail: Key (email)=() already exists.]
        if (this.userDao.existsByEmail(userDto.getEmail())) {
            // Handle duplicate email error (e.g., throw exception)
            throw new UserAlreadyExistsException(userDto.getEmail());
        }
        return this.userDao.save(userDto);
    }

    public Optional<UserDto> updateUser(String id, UserDto userDto) {
        return this.userDao.updateUser(id, userDto);
    }

    public Optional<UserDto> delete(String id) {
        return this.userDao.findById(id).map(userDto -> {
            this.userDao.delete(userDto);
            return userDto;
        });
    }
}
