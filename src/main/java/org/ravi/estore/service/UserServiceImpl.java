package org.ravi.estore.service;

import org.ravi.estore.model.User;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    public User createUser(String firstName, String lastName, String email, String password, String repeatPassword) {
        if(firstName == null || firstName.trim().length() == 0) {
            throw new IllegalArgumentException("User's First name is empty");
        }
        if(lastName == null || lastName.trim().length() == 0) {
            throw new IllegalArgumentException("User's First name is empty");
        }
        return new User(firstName, lastName, email, UUID.randomUUID().toString());

    }
}
