package com.ing.rankup_b.taskCompleted;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ing.rankup_b.adminManageTeam.AdminManageTeam;
import com.ing.rankup_b.task.Task;
import com.ing.rankup_b.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "task_completed")
public class TaskCompleted {
    private static final long serialVersionUID = 1L;

    enum Status {
        Sospeso, Accettato, Rifiutato
    }

    @Id
    @Column(name = "id_task_completed")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "attached")
    private String attached;

    @Column(name = "timestamp")
    @GeneratedValue(strategy = GenerationType.AUTO) //da verificare
    private Timestamp timestamp;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_task")
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private Task task;

    @ManyToOne
    @JoinColumn(name = "id_admin")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private AdminManageTeam admin;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @Column(name = "revision_date")
    private Date revisionDate;

    @Column(name = "bonus_points")
    private int bonus;

    @Column(name = "comment")
    private String comment;

    @Column(name = "status")
    private Status status;     //0 sospeso, 1 accettato, 2 rifiutato
}
