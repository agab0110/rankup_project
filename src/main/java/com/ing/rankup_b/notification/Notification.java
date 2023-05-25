package com.ing.rankup_b.notification;

import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ing.rankup_b.adminReciveNotification.AdminReciveNotification;
import com.ing.rankup_b.team.Team;
import com.ing.rankup_b.userReciveNotification.UserReciveNotification;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name = "notification")
public class Notification {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_notification")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "Il titolo non può essere vuoto")
    private String title;

    @Column(name = "description")
    @NotEmpty(message = "La descrizione non può essere vuota")
    private String description;

    @Column(name = "date")
    @NotEmpty(message = "La data non può essere vuota")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "id_team")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Team team;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<UserReciveNotification> user;

    @OneToMany
    @JsonIgnore
    private Set<AdminReciveNotification> admin;
}
