package org.ravi.estore.service;

import org.ravi.estore.model.User;

public class UserServiceImpl implements UserService {

    public User createUser(String firstName, String lastName, String email, String password, String repeatPassword) {
        return new User(firstName);

    }
}
