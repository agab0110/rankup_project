package com.ing.rankup_b.notification;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;

    }

    public ResponseEntity<?> newAdminNotification(long idTeam, int idAdmin, Notification notification) {
        
        Date date = new Date();

        notification.setDate(date);

        return ResponseEntity.status(HttpStatus.OK).body(this.notificationRepository.save(notification));
    }
}
