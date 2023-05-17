package com.ing.rankup_b.team;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ing.rankup_b.adminManageTeam.AdminManageTeam;
import com.ing.rankup_b.notification.Notification;
import com.ing.rankup_b.prize.Prize;
import com.ing.rankup_b.rule.Rule;
import com.ing.rankup_b.task.Task;
import com.ing.rankup_b.userJoinsTeam.UserJoinsTeam;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name = "team")
public class Team {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_team")
    private long codice;

    @Column(name = "name")
    @NotEmpty(message = "Il nome non può essere vuoto")
    private String name;

    @Column(name = "photo")
    private String photo;

    @Column(name = "privacy")
    private boolean privacy;    //0 privato, 1 pubblico

    @Column(name = "point_visibility")
    private boolean pointVisibility;    //0 non visibili, 1 visibili

    @Column(name = "prizes")
    @JsonIgnore
    @OneToMany(mappedBy = "beloggingTeam")
    private List<Prize> prizes;

    @Column(name = "notifications")
    @JsonIgnore
    @OneToMany(mappedBy = "team")
    private List<Notification> notifications;

    @JsonIgnore
    @OneToMany(mappedBy = "team")
    @Column(name = "rules")
    private List<Rule> rules;

    @JsonIgnore
    @OneToMany(mappedBy = "team")
    @Column(name = "tasks")
    private List<Task> tasks;

    @JsonIgnore
    @OneToMany(mappedBy = "team")
    private Set<UserJoinsTeam> userJoinsTeams;

    @JsonIgnore
    @OneToMany(mappedBy = "team")
    @JsonIgnore
    private Set<AdminManageTeam> adminsManageTeam;
}