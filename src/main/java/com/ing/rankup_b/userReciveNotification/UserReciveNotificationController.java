package com.ing.rankup_b.userReciveNotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("userReciveNotificationApi/")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8200", "http://localhost:4200"})
public class UserReciveNotificationController {
    
    @Autowired
    private UserReciveNotificationService service;

    public UserReciveNotificationController(UserReciveNotificationService service) {
        this.service = service;
    }
}
