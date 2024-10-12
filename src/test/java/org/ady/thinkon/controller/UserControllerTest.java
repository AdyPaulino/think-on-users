package org.ady.thinkon.controller;

import org.ady.thinkon.entity.User;
import org.ady.thinkon.service.UserService;
import org.junit.jupiter.api.AfterEach;
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

    private AutoCloseable closeable;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void getAllUsers_withEmptyDB_shouldReturnEmptyList() {
        when(userService.getAllUsers()).thenReturn(new ArrayList<>());

        List<User> result = userController.getAllUsers().getBody();

        assertThat(result).isEmpty();
    }

    @Test
    void getAllUsers_with1SavedUser_shouldReturnListWith1User() {
        when(userService.getAllUsers()).thenReturn(List.of(getUser()));

        List<User> result = userController.getAllUsers().getBody();

        assertThat(result).isNotEmpty()
                .hasSize(1);
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

    @Test
    void updateUser_withValidIdAndUser_shouldReturnUpdatedUser() {
        User originalUser = getUser();
        User userToBeUpdated = getUser();
        userToBeUpdated.setUsername("UpdatedUsername");
        when(userService.getUserById(1L)).thenReturn(originalUser);
        when(userService.updateUser(originalUser, userToBeUpdated)).thenReturn(userToBeUpdated);

        User result = userController.updateUser(1L, originalUser).getBody();

        assertThat(result).isNotNull().satisfies(
                user -> assertThat(user.getUsername()).isEqualTo("UpdatedUsername")
        );
    }

    @Test
    void deleteUser_withValidId_shouldReturnSuccessMessage() {
        User user = getUser();
        when(userService.getUserById(1L)).thenReturn(user);

        String result = userController.deleteUser(1L).getBody();

        assertThat(result).isEqualTo("User successfully deleted!");
    }

    private User getUser() {
        User user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setUsername("username");
        user.setEmail("email@email.com");
        user.setPhoneNumber("1234567890");

        return user;
    }
}
