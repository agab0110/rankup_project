package com.ing.rankup_b.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{
    
    @Query(value = "SELECT JSON_ARRAYAGG(JSON_OBJECT('id', notification.id_notification, 'date', notification.date, 'title', notification.title, 'teamName', team.name)) FROM notification JOIN team on notification.id_team = team.id_team JOIN user_recive_notification ON user_recive_notification.id_notification = notification.id_notification WHERE user_recive_notification.id_user = ?1 ", nativeQuery = true)
    String getUserNotification(int idUser);

    @Query(value = "SELECT JSON_ARRAYAGG(JSON_OBJECT('id', notification.id_notification, 'date', notification.date, 'title', notification.title, 'teamName', team.name)) FROM notification JOIN admin_recive_notification ON admin_recive_notification.id_notification = notification.id_notification JOIN admin_manage_team ON admin_recive_notification.id_admin = admin_manage_team.id_admin JOIN team ON team.id_team = admin_manage_team.id_team WHERE admin_manage_team.id_user = ?1", nativeQuery = true)
    String getAdminNotification(int idUser);
}
