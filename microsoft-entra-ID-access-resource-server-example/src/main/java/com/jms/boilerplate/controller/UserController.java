package com.jms.boilerplate.controller;

import com.jms.boilerplate.dto.UserDto;
import com.jms.boilerplate.exception.UserNotFoundException;
import com.jms.boilerplate.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
        GET /users
     */
    @GetMapping("")
    @ResponseBody
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    /*
        GET /users/:id
     */
    @GetMapping("/{id}")
    @ResponseBody
    public Optional<UserDto> getUser(
            @PathVariable
            String id
    ) {
        return userService.getUser(id);
    }

    /*
        POST /users
     */
    @PostMapping("")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('APPROLE_Contributor','APPROLE_Admin')")
    public UserDto createUser(
            @Valid
            @RequestBody
            UserDto userDto
    ) {
        return userService.createUser(userDto);
    }

    /*
        PUT /users/:id
     */
    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('APPROLE_Contributor','APPROLE_Admin')")
    public UserDto updateUser(
            @PathVariable
            String id,
            @Valid
            @RequestBody
            UserDto userDto
    ) {
        return userService.updateUser(id, userDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /*
        DELETE /users/:id
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('APPROLE_Admin')")
    public UserDto deleteUser(
            @PathVariable
            String id
    ) {
        return userService.delete(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
