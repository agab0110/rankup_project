package com.ing.rankup_b.userJoinsTeam;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ing.rankup_b.team.Team;
import com.ing.rankup_b.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_joins_team")
public class UserJoinsTeam {

    enum Status {
        Sospeso, Accettato, Rifiutato
    }
    
    /*@EmbeddedId
    @Column(name = "key")
    UserJoinsTeamKey key;*/

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    //@MapsId("idUser")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_team")
    //@MapsId("idTeam")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Team team;

    @Column(name = "points")
    private int points;

    @Column(name = "accepted")
    private Status status;   //0 sospeso, 1 accettato, 2 non accettato
}
