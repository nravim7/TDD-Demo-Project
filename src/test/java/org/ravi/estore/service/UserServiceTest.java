package org.ravi.estore.service;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ravi.estore.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest {
    UserService userService;
    String firstName;
    String lastName;
    String email;
    String password;
    String repeatPassword;




    @BeforeEach
    void init() {
        userService = new UserServiceImpl();
        firstName = "Ravi";
        lastName = "Nagaraju";
        email = "test@gmail.com";
        password = "12345";
        repeatPassword = "12345";

    }


    @DisplayName("User object created")
    @Test
    void testCreateUser_whenUserDetailsProvided_returnsUserObject() {
        //Arrange


        //Act
        User user = userService.createUser(firstName,lastName, email,password, repeatPassword);

        //Assert
        assertNotNull(user, "The createUser() should not have returned null");
        assertEquals(firstName, user.getFirstName(),"User's first name is incorrect");

        assertEquals(lastName, user.getLastName(), "User's last name is incorrect");
        assertEquals(email, user.getEmail(), "Email is different");
        assertNotNull(user.getId(),"User id is not generated");

    }

    @Test
    void testCreateUser_whenFirstNameIsEmpty_returnIllegalException() {
        //Arrange
        String firstName = "";
        String expectedExceptionMessage = "User's First name is empty";

        //Act
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    userService.createUser(firstName, lastName, email, password, repeatPassword);
                }, "Empty First Name should have caused an Illegal Argument exception");

        //Assert
        assertEquals(expectedExceptionMessage, thrown.getMessage(), "Exception error message is not correct");

    }

    @Test
    void testCreateUser_whenLastNameIsEmpty_returnIllegalException() {
        //Arrange
        String lastName = " ";
        String expectedExceptionMessage = "User's First name is empty";

        //Act
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, ()-> {
                    userService.createUser(firstName, lastName, email, password, repeatPassword);
                }, "Should have thrown an Exception");

        //Assert
        assertEquals(expectedExceptionMessage, thrown.getMessage(), "Exception are not same");
    }



    }

}
