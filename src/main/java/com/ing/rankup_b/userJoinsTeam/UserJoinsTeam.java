package com.ing.rankup_b.userJoinsTeam;

import com.ing.rankup_b.team.Team;
import com.ing.rankup_b.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_joins_team")
public class UserJoinsTeam {
    
    @EmbeddedId
    @Column(name = "key")
    UserJoinsTeamKey key;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @MapsId("idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_team")
    @MapsId("idTeam")
    private Team team;

    @Column(name = "points")
    private int points;

    @Column(name = "accepted")
    private boolean accepted;   //0 non accettato, 1 accettato
}
