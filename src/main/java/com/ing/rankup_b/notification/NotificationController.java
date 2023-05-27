package com.ing.rankup_b.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("notificationApi")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8200", "http://localhost:4200"})

public class NotificationController {
    
    @Autowired
    private NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }
    
    @GetMapping(path = "/userNotification/{idNotification}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUserNotification(@PathVariable int idNotification) {
        return this.service.getUserNotification(idNotification);
    }
}
