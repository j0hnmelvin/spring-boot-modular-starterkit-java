package com.jms.boilerplate.userservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jms.boilerplate.userservice.dto.UserDto;
import com.jms.boilerplate.userservice.exception.UserNotFoundException;
import com.jms.boilerplate.userservice.service.UserService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserControllerTest {

    @Test
    public void getUsers() {
        // GIVEN
        // Mock user service
        UserService mockUserService = Mockito.mock(UserService.class);
        List<UserDto> expectedResponse = Arrays.asList(UserDto.builder().name("John Doe").email("john.doe@example.com").build());
        Mockito.when(mockUserService.getUsers()).thenReturn(expectedResponse);
        // Inject mock user service into controller
        UserController userController = new UserController(mockUserService);

        // WHEN
        // Perform the request
        List<UserDto> actualResponse = userController.getUsers();

        // THEN
        assertEquals(actualResponse, expectedResponse);
    }

    @Test
    public void getUser() {
        // GIVEN
        // Mock user service
        UserService mockUserService = Mockito.mock(UserService.class);
        UserDto expectedResponse = UserDto.builder().id("existentId").name("John Doe").email("john.doe@example.com").build();
        Mockito.when(mockUserService.getUser("existentId")).thenReturn(Optional.ofNullable(expectedResponse));
        // Inject mock user service into controller
        UserController userController = new UserController(mockUserService);

        // WHEN
        // Perform the request
        UserDto actualResponse = userController.getUser("existentId");

        // THEN
        assertEquals(actualResponse, expectedResponse);
    }

    @Test
    public void getUserThrowUserNotFoundException() {
        // GIVEN
        // Mock UserService
        UserService userService = Mockito.mock(UserService.class);
        UserNotFoundException expectedException = new UserNotFoundException("nonexistentId");
        Mockito.when(userService.getUser("nonexistentId")).thenThrow(expectedException);
        // Inject mock user service into controller
        UserController userController = new UserController(userService);

        // WHEN
        // Perform the request
        UserNotFoundException actualException = null;
        try {
            userController.getUser("nonexistentId");
        } catch (UserNotFoundException exception) {
            actualException = exception;
        }

        // THEN
        // Expect UserNotFoundException
        // assertThrows(UserNotFoundException.class, () -> userController.getUser("nonexistentId"));
        assertEquals(actualException, expectedException);
    }

    @Test
    public void createUser() {
        // GIVEN
        // Mock user service
        UserService mockUserService = Mockito.mock(UserService.class);
        UserDto newUserDto = UserDto.builder().name("John Doe").email("john.doe@example.com").build();
        UserDto expectedResponse = UserDto.builder().id("qwertymnbvc").name("John Doe").email("john.doe@example.com").build();
        Mockito.when(mockUserService.createUser(newUserDto)).thenReturn(expectedResponse);
        // Inject mock user service into controller
        UserController userController = new UserController(mockUserService);

        // WHEN
        // Perform the request
        UserDto actualResponse = userController.createUser(newUserDto);

        // THEN
        assertEquals(actualResponse, expectedResponse);
    }@Test

    public void updateUser() {
        // GIVEN
        // Mock user service
        UserService mockUserService = Mockito.mock(UserService.class);
        UserDto existingUserDto = UserDto.builder().id("existentId").name("John Doe").email("john.doe@example.com").build();
        UserDto expectedResponse = UserDto.builder().id("existentId").name("Johnny Doe").email("johnnyu.doe@example.com").build();
        Mockito.when(mockUserService.updateUser("existentId", existingUserDto)).thenReturn(Optional.ofNullable(expectedResponse));
        // Inject mock user service into controller
        UserController userController = new UserController(mockUserService);

        // WHEN
        // Perform the request
        UserDto actualResponse = userController.updateUser("existentId", existingUserDto);

        // THEN
        assertEquals(actualResponse, expectedResponse);
    }

    @Test
    public void deleteUser() {
        // GIVEN
        // Mock user service
        UserService mockUserService = Mockito.mock(UserService.class);
        UserDto expectedResponse = UserDto.builder().id("existentId").name("John Doe").email("john.doe@example.com").build();
        Mockito.when(mockUserService.deleteUser("existentId")).thenReturn(Optional.ofNullable(expectedResponse));
        // Inject mock user service into controller
        UserController userController = new UserController(mockUserService);

        // WHEN
        // Perform the request
        UserDto actualResponse = userController.deleteUser("existentId");

        // THEN
        assertEquals(actualResponse, expectedResponse);
    }
}
