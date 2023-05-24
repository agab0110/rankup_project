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

    public ResponseEntity getUserNotification(int idUser){
        List<Notification> userNotification = new ArrayList<>();
        for(Notification n : this.repository.findAll()){
            if(n.getUser().getId() == idUser){
                userNotification.add(n);
            }
        }
        if(!userNotification.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(userNotification);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nessuna notifica presente");
        }
    }
}
