package org.ravi.estore.service;

import org.ravi.estore.model.User;

public interface UserService {

    User createUser(String firstName, String lastName, String email, String password, String repeatPassword);
}
