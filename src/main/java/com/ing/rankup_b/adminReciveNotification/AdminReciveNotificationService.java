package com.ing.rankup_b.adminReciveNotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminReciveNotificationService {

    @Autowired
    private AdminReciveNotificationRepository repository;

    public AdminReciveNotificationService(AdminReciveNotificationRepository repository) {
        this.repository = repository;
    }
}
