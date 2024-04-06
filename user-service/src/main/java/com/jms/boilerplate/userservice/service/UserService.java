package com.jms.boilerplate.userservice.service;

import com.jms.boilerplate.userservice.domain.User;
import com.jms.boilerplate.userservice.dto.UserDto;
import com.jms.boilerplate.userservice.mapper.UserMapper;
import com.jms.boilerplate.userservice.repo.UserRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<UserDto> getUsers() {
        return this.userRepo.findAll().stream().map(user -> {
            return UserMapper.INSTANCE.toUserDto(user);
        }).toList();
    }

    public Optional<UserDto> getUser(Long id) {
        return this.userRepo.findById(id).map(user -> {
            return UserMapper.INSTANCE.toUserDto(user);
        });
    }

    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.INSTANCE.toUser(userDto);
        // TODO: Handle ERROR: duplicate key value violates unique constraint "" Detail: Key (email)=() already exists.]
        user = this.userRepo.save(user);  // Save the User object directly
        return UserMapper.INSTANCE.toUserDto(user);  // Map the saved User object
    }

    public Optional<UserDto> updateUser(Long id, UserDto userDto) {
        return this.userRepo.findById(id).map(user -> {
            UserMapper.INSTANCE.updateUserFromDto(userDto, user);
            return UserMapper.INSTANCE.toUserDto(
                this.userRepo.save(user)
            );
        });
    }

    public Optional<UserDto> delete(Long id) {
        return this.userRepo.findById(id).map(user -> {
            this.userRepo.delete(user);
            return UserMapper.INSTANCE.toUserDto(user);
        });
    }
}
