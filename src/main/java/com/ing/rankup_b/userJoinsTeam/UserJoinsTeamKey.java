package com.ing.rankup_b.userJoinsTeam;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class UserJoinsTeamKey implements Serializable {
    @Column(name = "user")
    private int idUser;

    @Column(name = "team")
    private long idTeam;
}
