package org.ravi.estore.service;

import org.ravi.estore.data.UserRepository;
import org.ravi.estore.data.UserRepositoryImpl;
import org.ravi.estore.model.User;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String firstName, String lastName, String email, String password, String repeatPassword) {
        if(firstName == null || firstName.trim().length() == 0) {
            throw new IllegalArgumentException("User's First name is empty");
        }
      
        if(lastName == null || lastName.trim().length() == 0) {
            throw new IllegalArgumentException("User's First name is empty");
        }
        User user = new User(firstName, lastName, email, UUID.randomUUID().toString());


        boolean isUserCreated =  userRepository.save(user);
        if(!isUserCreated) throw new UserServiceException("Could not create user");

        return user;

    }
}
