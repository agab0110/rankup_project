package com.ing.rankup_b.rule;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ing.rankup_b.adminManageTeam.AdminManageTeam;
import com.ing.rankup_b.ruleCompleted.RuleCompleted;
import com.ing.rankup_b.team.Team;

import jakarta.persistence.CascadeType;
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
@Table(name = "rule")
public class Rule {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_rule")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Il nome non può essere vuoto")
    private String name;

    @Column(name = "points")
    @NotNull(message = "Il punteggio non può essere vuoto")
    @Positive
    private int points;

    @Column(name = "description")
    @NotEmpty(message = "La descrizione non può essere vuota")
    private String description;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_team")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "id_admin")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AdminManageTeam admin;

    @Column(name = "rulesCompleted")
    @OneToMany(mappedBy = "rule")
    @JsonIgnore
    private List<RuleCompleted> rulesCompleted;
}
