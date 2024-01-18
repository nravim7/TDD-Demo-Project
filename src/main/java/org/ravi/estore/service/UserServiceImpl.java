package org.ravi.estore.service;

import org.ravi.estore.data.UserRepository;
import org.ravi.estore.data.UserRepositoryImpl;
import org.ravi.estore.model.User;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    EmailNotificationService emailNotificationService;

    public UserServiceImpl(UserRepository userRepository, EmailNotificationService emailNotificationService) {
        this.userRepository = userRepository;
        this.emailNotificationService = emailNotificationService;
    }

    public User createUser(String firstName, String lastName, String email, String password, String repeatPassword) {
        if (firstName == null || firstName.trim().length() == 0) {
            throw new IllegalArgumentException("User's First name is empty");
        }

        if (lastName == null || lastName.trim().length() == 0) {
            throw new IllegalArgumentException("User's First name is empty");
        }
        User user = new User(firstName, lastName, email, UUID.randomUUID().toString());

        boolean isUserCreated;

        try {
            isUserCreated = userRepository.save(user);
        } catch (RuntimeException ex) {
            throw new UserServiceException(ex.getMessage());
        }

        if (!isUserCreated) throw new UserServiceException("Could not create user");

        try {
            emailNotificationService.scheduleEmailNotification(user);
        } catch (RuntimeException ex) {
            throw  new UserServiceException(ex.getMessage());
        }

        return user;
    }


}
