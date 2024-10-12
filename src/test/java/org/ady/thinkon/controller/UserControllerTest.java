package org.ady.thinkon.controller;

import org.ady.thinkon.entity.User;
import org.ady.thinkon.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Class to test the UserController methods
 *
 * @author Ady Paulino 
 */
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllUsers_withEmptyDB_shouldReturnEmptyList() {
        when(userService.getAllUsers()).thenReturn(new ArrayList<>());

        List<User> result = userController.getAllUsers().getBody();

        assertThat(result).isEmpty();
    }

    @Test
    void createUser_withValidUser_shouldReturnCreatedUser() {
        User user = getUser();
        when(userService.createUser(user)).thenReturn(user);

        User result = userController.createUser(user).getBody();

        assertThat(result).isEqualTo(user);
    }

    @Test
    void getUserById_withValidId_shouldReturnCorrectUser() {
        User user = getUser();
        when(userService.getUserById(1L)).thenReturn(user);

        User result = userController.getUserById(1L).getBody();

        assertThat(result).isEqualTo(user);
    }

    private User getUser() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setUsername("username");
        user.setEmail("email@email.com");
        user.setPhoneNumber("1234567890");

        return user;
    }
}
