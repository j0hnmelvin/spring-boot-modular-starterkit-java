package com.jms.boilerplate.mongodbuserservice.dao;

import com.jms.boilerplate.mongodbuserservice.mapper.UserMapper;
import com.jms.boilerplate.mongodbuserservice.repo.UserRepo;
import com.jms.boilerplate.userservice.dao.UserDao;
import com.jms.boilerplate.userservice.dto.UserDto;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl implements UserDao {
    private final UserRepo userRepo;

    public UserDaoImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<UserDto> findAll() {
        return this.userRepo.findAll().stream().map(user -> {
            return UserMapper.INSTANCE.toUserDto(user);
        }).toList();
    }

    @Override
    public Optional<UserDto> findById(String id) {
        return this.userRepo.findById(id).map(user -> {
            return UserMapper.INSTANCE.toUserDto(user);
        });
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepo.existsByEmail(email);
    }

    @Override
    public UserDto save(UserDto userDto) {
        return UserMapper.INSTANCE.toUserDto(
                this.userRepo.save(UserMapper.INSTANCE.toUser(userDto))
        );
    }

    @Override
    public Optional<UserDto> updateUser(String id, UserDto userDto) {
        return this.userRepo.findById(id).map(user -> {
            UserMapper.INSTANCE.updateUserFromDto(userDto, user);
            return UserMapper.INSTANCE.toUserDto(
                    this.userRepo.save(user)
            );
        });
    }

    @Override
    public void delete(UserDto userDto) {
        this.userRepo.delete(UserMapper.INSTANCE.toUser(userDto));
    }
}
