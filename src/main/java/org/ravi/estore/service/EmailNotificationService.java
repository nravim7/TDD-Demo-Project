package org.ravi.estore.service;

import org.ravi.estore.model.User;

public interface EmailNotificationService {

    void scheduleEmailNotification(User user);
}
