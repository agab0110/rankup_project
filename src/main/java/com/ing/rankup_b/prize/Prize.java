package com.ing.rankup_b.prize;

import java.util.Set;

import com.ing.rankup_b.adminManageTeam.AdminManageTeam;
import com.ing.rankup_b.team.Team;
import com.ing.rankup_b.userGetPrize.UserGetPrize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "prize")
public class Prize {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prize")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Il nome non può essere vuoto")
    private String name;

    @Column(name = "price")
    @NotNull(message = "Il costo non può essere vuoto")
    private int price;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private Team beloggingTeam;

    @ManyToOne
    @JoinColumn(name = "id_admin")
    private AdminManageTeam admin;

    @OneToMany(mappedBy = "prize")
    private Set<UserGetPrize> userGetPrizes;
}
