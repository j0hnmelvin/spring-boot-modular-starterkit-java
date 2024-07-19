package com.jms.boilerplate.userservice.dao;

import com.jms.boilerplate.userservice.dto.UserDto;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<UserDto> findAll();

    Optional<UserDto> findById(String id);

    boolean existsByEmail(String email);

    UserDto save(UserDto userDto) ;

    Optional<UserDto> updateUser(String id, UserDto user);

    void delete(UserDto user);
}
