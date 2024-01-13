package org.ravi.estore.service;


import org.junit.jupiter.api.Test;
import org.ravi.estore.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest {

    @Test
    void testCreateUser_whenUserDetailsProvided_returnsUserObject() {
        //Arrange
        UserService userService = new UserServiceImpl();
        String firstName = "Ravi";
        String lastName = "Nagaraju";
        String email = "test@gmail.com";
        String password = "12345";
        String repeatPassword = "12345";

        //Act
        User user = userService.createUser(firstName,lastName, email,password, repeatPassword);

        //Assert
        assertNotNull(user, "The createUser() should not have returned null");

    }

    @Test
    void testCreateUser_whenUserCreated_returnedUserObjectContainsSameFirstName() {
        //Arrange
        UserService userService = new UserServiceImpl();
        String firstName = "Ravi";
        String lastName = "Nagaraju";
        String email = "test@gmail.com";
        String password = "12345";
        String repeatPassword = "12345";

        //Act
        User user = userService.createUser(firstName,lastName, email,password, repeatPassword);

        //Assert
        assertEquals(firstName, user.getFirstName());
    }
}
