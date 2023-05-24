package com.ing.rankup_b.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    /*
     * N.62
     */
    public String getUserNotification(int idUser) {
        return this.repository.getUserNotification(idUser);
    }

    /*
     * N.62
     */
    public String getAdminNotification(int idUser) {
        return this.repository.getAdminNotification(idUser);
    }
}
