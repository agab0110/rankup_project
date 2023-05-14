package com.ing.rankup_b.userGetPrize;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface UserGetPrizeRepository extends JpaRepository<UserGetPrize, UserGetPrizeKey> {
    @Query(value = "SELECT prize.id_prize, prize.name, prize.price FROM user_get_prize JOIN prize ON user_get_prize.id_prize = prize.id_prize WHERE prize.team = ?1 AND user_get_prize.id_user = ?2", nativeQuery = true)
    public ArrayList<String> userPrize(int id_team, int id_user);
}
