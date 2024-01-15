package org.ravi.estore.data;

import org.ravi.estore.model.User;

public interface UserRepository {

    boolean save(User user);
}
