package com.ing.rankup_b.adminManageTeam;

import java.util.Set;

import com.ing.rankup_b.prize.Prize;
import com.ing.rankup_b.rule.Rule;
import com.ing.rankup_b.ruleCompleted.RuleCompleted;
import com.ing.rankup_b.task.Task;
import com.ing.rankup_b.taskCompleted.TaskCompleted;
import com.ing.rankup_b.team.Team;
import com.ing.rankup_b.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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

    @EmbeddedId
    @Column(name = "key")
    private AdminManageTeamKey key;

    @ManyToOne
    @JoinColumn(name = "id_team")
    @MapsId("idTeam")
    private Team team;
    
    @ManyToOne
    @JoinColumn(name = "id_user")
    @MapsId("idUser")
    private User user;
    
    @OneToMany(mappedBy = "admin")
    private Set<Prize> prizes;

    @OneToMany(mappedBy = "creatorAdmin")
    private Set<Team> teams;

    @OneToMany(mappedBy = "admin")
    private Set<Rule> rules;

    @OneToMany(mappedBy = "admin")
    private Set<RuleCompleted> rulesCompleted;

    @OneToMany(mappedBy = "admin")
    private Set<Task> tasks;

    @OneToMany(mappedBy = "admin")
    private Set<TaskCompleted> tasksCompleted;
}
