package org.ravi.estore.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ravi.estore.data.UserRepository;
import org.ravi.estore.model.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    EmailNotificationService emailNotificationService;
    String firstName;
    String lastName;
    String email;
    String password;
    String repeatPassword;


    @BeforeEach
    void init() {
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
        when(userRepository.save(any(User.class))).thenReturn(true);


        //Act
        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);

        //Assert
        assertNotNull(user, "The createUser() should not have returned null");
        assertEquals(firstName, user.getFirstName(), "User's first name is incorrect");

        assertEquals(lastName, user.getLastName(), "User's last name is incorrect");
        assertEquals(email, user.getEmail(), "Email is different");
        assertNotNull(user.getId(), "User id is not generated");

        verify(userRepository).save(any(User.class)); // same as verify(userRepository, times(1)).save(any(User.class));

    }

    @Test
    void testCreateUser_whenFirstNameIsEmpty_returnIllegalException() {
        //Arrange
        String firstName = "";
        String expectedExceptionMessage = "User's First name is empty";

        //Act
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
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
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(firstName, lastName, email, password, repeatPassword);
        }, "Should have thrown an Exception");

        //Assert
        assertEquals(expectedExceptionMessage, thrown.getMessage(), "Exception are not same");
    }


    @DisplayName("")
    @Test
    void testCreateUser_whenSaveMethodThrowsException_thenThrowsUserServiceException() {
        //Arrange
        when(userRepository.save(any(User.class))).thenThrow(RuntimeException.class);


        //Act & Assert
        assertThrows(UserServiceException.class, () -> {
            userService.createUser(firstName, lastName, email, password, repeatPassword);
        }, "Should have thrown UserServiceException instead");

        //Assert

    }


    @Test
    void testCreateUser_whenEmailNotificationExceptionThrown_throwsUserServiceException() {
        //Arrange
        when(userRepository.save(any(User.class))).thenReturn(true);

        doThrow(EmailNotificationException.class).when(emailNotificationService).scheduleEmailNotification(any(User.class));

        //Act
        assertThrows(UserServiceException.class, () -> {
            userService.createUser(firstName, lastName, email, password, repeatPassword);

        }, "Should have thrown UserServiceException instead");


        //Assert
        verify(emailNotificationService, times(1)).scheduleEmailNotification(any(User.class));
    }


}

