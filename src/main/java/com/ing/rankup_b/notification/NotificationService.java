package com.ing.rankup_b.notification;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ing.rankup_b.user.User;
import org.springframework.stereotype.Service;

import com.ing.rankup_b.team.Team;
import com.ing.rankup_b.team.TeamRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private TeamRepository teamRepository;

    public NotificationService(NotificationRepository notificationRepository, TeamRepository teamRepository) {
        this.notificationRepository = notificationRepository;
        this.teamRepository = teamRepository;
    }

    public ResponseEntity getUserNotification(int idNotification) {
        Notification notification = new Notification();

        for (Notification n : this.notificationRepository.findAll()) {
            if (n.getId() == idNotification) {
                notification = n;
                return ResponseEntity.status(HttpStatus.OK).body(notification);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Notifica non trovata");
    }

    /**
     * Funzione per creare una nuova notifica per un determinato team
     * 
     * @param idTeam       il team per cui viene creata la notifica
     * @param notification il body della notifica composto solo da title e
     *                     description
     * @return (200 OK) con la notifica salvata nel body
     */
    public ResponseEntity<?> newNotification(long idTeam, Notification notification) {
        Team team = this.teamRepository.findById(idTeam).get();
        Date date = new Date();

        notification.setDate(date);
        notification.setTeam(team);

        return ResponseEntity.status(HttpStatus.OK).body(this.notificationRepository.save(notification));
    }

    /*
     * N.62
     */
    public String getUserNotifications(int idUser) {
        return this.notificationRepository.getUserNotification(idUser);
    }

    /*
     * N.62
     */
    public String getAdminNotification(int idUser) {
        return this.notificationRepository.getAdminNotification(idUser);
    }

    /*
     * Notifiche
     */
    public String userNotificationDisplayed(int idUser) {
        return this.notificationRepository.userNotificationDisplayed(idUser);
    }

    /*
     * Notifiche
     */
    public String adminNotificationDisplayed(int idUser) {
        return this.notificationRepository.adminNotificationDisplayed(idUser);
    }

    /*
     * Notifiche
     */
    public String getUserDisplayed(int idUser) {
        return this.notificationRepository.getUserDisplayed(idUser);
    }

    /*
     * Notifiche
     */
    public String getAdminDisplayed(int idUser) {
        return this.notificationRepository.getAdminDisplayed(idUser);
    }
}
