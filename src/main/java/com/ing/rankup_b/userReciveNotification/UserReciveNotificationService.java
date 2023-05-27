package com.ing.rankup_b.userReciveNotification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserReciveNotificationService {
    
    @Autowired
    private UserReciveNotificationRepository repository;

    public UserReciveNotificationService(UserReciveNotificationRepository repository) {
        this.repository = repository;
    }
     /**
     * serve per prendere tutte le notifiche del singolo utente
     * @param idUser e il parametro per prendere le notifiche del singolo utente
     * @return 200 ok o 400 bad request
     */
    public ResponseEntity<?> getNotification(int idUser) {
        List<UserReciveNotification> notifications = new ArrayList<>();
       for (UserReciveNotification n : this.repository.findAll()) {
              if (n.getUser().getId()== idUser) {
                    notifications.add(n);
              }
       }

         if (notifications.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Non ci sono notifiche per questo team");
         }
            return ResponseEntity.status(HttpStatus.OK).body(notifications);
    }
}
