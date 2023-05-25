package com.ing.rankup_b.userReciveNotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserReciveNotificationService {
    
    @Autowired
    private UserReciveNotificationRepository repository;

    public UserReciveNotificationService(UserReciveNotificationRepository repository) {
        this.repository = repository;
    }
}
