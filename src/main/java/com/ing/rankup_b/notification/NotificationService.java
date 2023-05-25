package com.ing.rankup_b.notification;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<?> newNotification(Notification notification) {
        Date date = new Date();
        notification.setDate(date);
        return ResponseEntity.status(HttpStatus.OK).body(this.repository.save(notification));
    }
}
