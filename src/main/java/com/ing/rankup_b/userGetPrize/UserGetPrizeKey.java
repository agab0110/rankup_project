package com.ing.rankup_b.userGetPrize;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class UserGetPrizeKey implements Serializable {
    @Column(name = "user")
    private int idUser;

    @Column(name = "prize")
    private int idPrize;
}
