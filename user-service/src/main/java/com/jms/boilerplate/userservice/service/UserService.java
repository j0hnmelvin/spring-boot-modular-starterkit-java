package com.jms.boilerplate.userservice.service;

import com.jms.boilerplate.userservice.dto.UserDto;
import com.jms.boilerplate.userservice.util.MockDataService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final MockDataService mockDataService;

    public UserService(MockDataService mockDataService) {
        this.mockDataService = mockDataService;
    }

    public List<UserDto> getUsers() {
        return this.mockDataService.users;
    }

    public Optional<UserDto> getUser(String id) {
        return this.mockDataService.users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public UserDto createUser(UserDto userDto) {
        if (!this.mockDataService.users.contains(userDto)) {
            this.mockDataService.users.add(userDto);
        }
        return userDto;
    }

    public Optional<UserDto> updateUser(String id, UserDto userDto) {
        return this.getUser(id).map(user -> {
            int index = this.mockDataService.users.indexOf(user);
            this.mockDataService.users.set(index, userDto);
            return userDto;
        });
    }

    public Optional<UserDto> delete(String id) {
        return this.getUser(id).map(user -> {
            int index = this.mockDataService.users.indexOf(user);
            this.mockDataService.users.remove(index);
            return user;
        });
    }
}
