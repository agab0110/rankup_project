package com.ing.rankup_b.ruleCompleted;

import java.sql.Timestamp;
import java.util.Date;

import com.ing.rankup_b.adminManageTeam.AdminManageTeam;
import com.ing.rankup_b.rule.Rule;
import com.ing.rankup_b.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "rule_completed")
public class RuleCompleted {
    private static final long serialVersionUID = 1L;

    enum Status {
        Sospeso, Accettato, Rifiutato
    }

    @Id
    @Column(name = "id_rule_completed")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "attached")
    private String attached;

    @Column(name = "timestamp")
    @GeneratedValue(strategy = GenerationType.AUTO) //da verificare
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "id_rule")
    private Rule rule;

    @ManyToOne
    @JoinColumn(name = "id_admin")
    private AdminManageTeam admin;

    @ManyToOne
    @JoinColumn(name = "id_user")
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
