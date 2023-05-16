package com.ing.rankup_b.adminManageTeam;

import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ing.rankup_b.prize.Prize;
import com.ing.rankup_b.rule.Rule;
import com.ing.rankup_b.ruleCompleted.RuleCompleted;
import com.ing.rankup_b.task.Task;
import com.ing.rankup_b.taskCompleted.TaskCompleted;
import com.ing.rankup_b.team.Team;
import com.ing.rankup_b.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "admin_manage_team")
public class AdminManageTeam {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_team")
    @MapsId("idTeam")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Team team;
    
    @ManyToOne
    @JoinColumn(name = "id_user")
    @MapsId("idUser")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    
    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private Set<Prize> prizes;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private Set<Rule> rules;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private Set<RuleCompleted> rulesCompleted;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private Set<Task> tasks;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private Set<TaskCompleted> tasksCompleted;
}
