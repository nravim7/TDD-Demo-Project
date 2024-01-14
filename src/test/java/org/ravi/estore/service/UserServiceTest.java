package org.ravi.estore.service;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ravi.estore.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest {

    @DisplayName("User object created")
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
        assertEquals(firstName, user.getFirstName(),"User's first name is incorrect");

    }

}
