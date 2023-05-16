package com.ing.rankup_b.userGetPrize;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ing.rankup_b.prize.Prize;
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
@Table(name = "user_get_prize")
public class UserGetPrize {
    
    @EmbeddedId
    @Column(name = "key")
    private UserGetPrizeKey key;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @MapsId("idUser")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_prize")
    @MapsId("idPrize")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Prize prize;

    @Column(name = "date")
    private Date date;

}
