package com.jms.boilerplate.userservice.controller;

import com.jms.boilerplate.userservice.dto.UserDto;
import com.jms.boilerplate.userservice.exception.UserNotFoundException;
import com.jms.boilerplate.userservice.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*") // Allow all origins for development
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /users
    @GetMapping("")
    @ResponseBody
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    // GET /users/:id
    @GetMapping("/{id}")
    @ResponseBody
    public UserDto getUser(
            @PathVariable
            String id
    ) {
        return userService.getUser(id)
            .orElseThrow(() -> new UserNotFoundException(id));
    }

    // POST /users
    @PostMapping("")
    @ResponseBody
//    @PreAuthorize("hasAnyAuthority('APPROLE_MFE1.Admin','APPROLE_MFE2.ADMIN')")
    public UserDto createUser(
            @Valid
            @RequestBody
            UserDto userDto
    ) {
        return userService.createUser(userDto);
    }

    // PUT /users/:id
    @PutMapping("/{id}")
    @ResponseBody
//    @PreAuthorize("hasAnyAuthority('APPROLE_MFE1.Admin','APPROLE_MFE2.ADMIN')")
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

    // DELETE /users/:id
    @DeleteMapping("/{id}")
    @ResponseBody
//    @PreAuthorize("hasAnyAuthority('APPROLE_MFE1.Admin','APPROLE_MFE2.ADMIN')")
    public UserDto deleteUser(
            @PathVariable
            String id
    ) {
        return userService.deleteUser(id)
            .orElseThrow(() -> new UserNotFoundException(id));
    }
}
