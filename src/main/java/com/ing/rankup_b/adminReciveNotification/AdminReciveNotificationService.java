package com.ing.rankup_b.adminReciveNotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ing.rankup_b.adminManageTeam.AdminManageTeam;
import com.ing.rankup_b.adminManageTeam.AdminManageTeamRepository;
import com.ing.rankup_b.notification.Notification;
import com.ing.rankup_b.notification.NotificationRepository;

@Service
public class AdminReciveNotificationService {

    @Autowired
    private AdminReciveNotificationRepository adminReciveNotificationRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private AdminManageTeamRepository adminManageTeamRepository;

    public AdminReciveNotificationService(AdminReciveNotificationRepository adminReciveNotificationRepository, NotificationRepository notificationRepository, AdminManageTeamRepository adminManageTeamRepository) {
        this.adminReciveNotificationRepository = adminReciveNotificationRepository;
        this.notificationRepository = notificationRepository;
        this.adminManageTeamRepository = adminManageTeamRepository;
    }

    /**
     * Funzione per collegare una notifica ad un admin
     * @param idAdmin l'admin a cui collegare la notifica
     * @param idNotification la notifica da collegare
     * @return 
     */
    public ResponseEntity<?> newNotification(int idAdmin, int idNotification) {
        AdminManageTeam admin = this.adminManageTeamRepository.findById(idAdmin).get();
        Notification notification = this.notificationRepository.findById(idNotification).get();

        if (admin == null || notification == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("admin o notifica non trovato");
        }

        AdminReciveNotification adminReciveNotification = new AdminReciveNotification();
        adminReciveNotification.setAdmin(admin);
        adminReciveNotification.setNotification(notification);

        return ResponseEntity.status(HttpStatus.OK).body(this.adminReciveNotificationRepository.save(adminReciveNotification));
    }
}
