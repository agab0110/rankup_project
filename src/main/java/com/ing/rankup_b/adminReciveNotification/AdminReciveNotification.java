package com.ing.rankup_b.adminReciveNotification;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ing.rankup_b.adminManageTeam.AdminManageTeam;
import com.ing.rankup_b.notification.Notification;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "admin_recive_notification")
public class AdminReciveNotification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "id_admin")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AdminManageTeam admin;

    @ManyToOne
    @JoinColumn(name = "id_notification")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Notification notification;
}