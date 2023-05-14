package com.ing.rankup_b.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notificationApi")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8200", "http://localhost:4200"})

public class NotificationController {
    
    @Autowired
    private NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }    
}
