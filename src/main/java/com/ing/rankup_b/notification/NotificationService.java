package com.ing.rankup_b.notification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ing.rankup_b.user.User;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }
    
    public ResponseEntity getUserNotification(int idNotification) {
        Notification notification = new Notification();

        for(Notification n : this.repository.findAll()){
            if(n.getId() == idNotification){
                notification = n;
                return ResponseEntity.status(HttpStatus.OK).body(notification);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Notifica non trovata");
    }
}
