package com.ing.rankup_b.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{
    
    @Query(value = "SELECT JSON_ARRAYAGG(JSON_OBJECT('date', notification.date, 'title', notification.title, 'teamName', team.name)) FROM notification JOIN team on notification.id_team=team.id_team JOIN user_joins_team ON team.id_team=user_joins_team.id_team WHERE notification.id_user = ?1", nativeQuery = true)
    String getUserNotification(int idUser);

    @Query(value = "SELECT JSON_ARRAYAGG(JSON_OBJECT('date', notification.date, 'title', notification.title, 'teamName', team.name)) FROM notification JOIN team on notification.id_team=team.id_team JOIN admin_manage_team ON notification.id_user = admin_manage_team.id_user WHERE notification.id_user = ?1 AND notification.id_notification NOT IN (SELECT notification.id_notification FROM notification JOIN team on notification.id_team=team.id_team JOIN user_joins_team ON team.id_team=user_joins_team.id_team WHERE notification.id_user = ?1)", nativeQuery = true)
    String getAdminNotification(int idUser);
}
