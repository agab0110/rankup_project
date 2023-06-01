package com.ing.rankup_b.userReciveNotification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ing.rankup_b.notification.Notification;
import com.ing.rankup_b.notification.NotificationRepository;
import com.ing.rankup_b.user.User;
import com.ing.rankup_b.user.UserRepository;

@Service
public class UserReciveNotificationService {
    
    @Autowired
    private UserReciveNotificationRepository userReciveNotificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public UserReciveNotificationService(UserReciveNotificationRepository userReciveNotificationRepository, UserRepository userRepository, NotificationRepository notificationRepository) {
        this.userReciveNotificationRepository = userReciveNotificationRepository;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
    }
     /**
     * serve per prendere tutte le notifiche del singolo utente
     * @param idUser e il parametro per prendere le notifiche del singolo utente
     * @return 200 ok o 400 bad request
     */
    public ResponseEntity<?> getNotification(int idUser) {
        List<UserReciveNotification> notifications = new ArrayList<>();
       for (UserReciveNotification n : this.userReciveNotificationRepository.findAll()) {
              if (n.getUser().getId()== idUser) {
                    notifications.add(n);
              }
       }

         if (notifications.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Non ci sono notifiche per questo team");
         }
            return ResponseEntity.status(HttpStatus.OK).body(notifications);
    }

    /**
     * Funzione per collegare una notifica ad un utente
     * @param idUser l'utente a cui collegare la notifica
     * @param idNotification la notifica da collegare
     * @return 200 ok o 400 bad request
     */
    public ResponseEntity<?> newNotification(int idUser, int idNotification) {
        User user = this.userRepository.findById(idUser).get();
        Notification notification = this.notificationRepository.findById(idNotification).get();

        if (user == null || notification == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user o notifica non trovato");
        }

        UserReciveNotification userReciveNotification = new UserReciveNotification();
        userReciveNotification.setUser(user);
        userReciveNotification.setNotification(notification);

        return ResponseEntity.status(HttpStatus.OK).body(this.userReciveNotificationRepository.save(userReciveNotification));
    }
}
