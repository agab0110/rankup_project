package com.ing.rankup_b.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificationApi")
@CrossOrigin

public class NotificationController {

    @Autowired
    private NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping(path = "/userNotification/{idNotification}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserNotification(@PathVariable int idNotification) {
        return this.service.getUserNotification(idNotification);
    }

    @PostMapping(path = "/newNotification/{idTeam}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newNotification(@PathVariable long idTeam, @RequestBody Notification notification) {
        return this.service.newNotification(idTeam, notification);
    }

    /**
     * N.62 P1
     */
    @GetMapping(path = "/getUserNotification/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserNotification1(@PathVariable int idUser) {
        String allNotification = this.service.getUserNotifications(idUser);

        if (allNotification == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Notifiche non trovate");
        }

        return ResponseEntity.status(HttpStatus.OK).body(allNotification);
    }

    /**
     * N.62 P2
     */
    @GetMapping(path = "/getAdminNotification/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAdminNotification(@PathVariable int idUser) {
        String allNotification = this.service.getAdminNotification(idUser);

        if (allNotification == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Notifiche non trovate");
        }

        return ResponseEntity.status(HttpStatus.OK).body(allNotification);
    }

    /**
     * Notifiche
     */
    @GetMapping(path = "/userNotificationDisplayed/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> userNotificationDisplayed(@PathVariable int idUser) {
        String displayed = this.service.userNotificationDisplayed(idUser);

        return ResponseEntity.status(HttpStatus.OK).body(displayed);
    }

    /**
     * Notifiche
     */
    @GetMapping(path = "/adminNotificationDisplayed/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> adminNotificationDisplayed(@PathVariable int idUser) {
        String displayed = this.service.adminNotificationDisplayed(idUser);

        return ResponseEntity.status(HttpStatus.OK).body(displayed);
    }

    /**
     * Notifiche
     */
    @GetMapping(path = "/getUserDisplayed/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserDisplayed(@PathVariable int idUser) {
        String allNotification = this.service.getUserDisplayed(idUser);

        return ResponseEntity.status(HttpStatus.OK).body(allNotification);
    }

    /**
     * Notifiche
     */
    @GetMapping(path = "/getAdminDisplayed/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAdminDisplayed(@PathVariable int idUser) {
        String allNotification = this.service.getAdminDisplayed(idUser);

        return ResponseEntity.status(HttpStatus.OK).body(allNotification);
    }
}
