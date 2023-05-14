package com.ing.rankup_b.prize;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface PrizeRepository extends JpaRepository<Prize, Integer> {

    @Query(value = "SELECT prize.name, prize.price FROM prize JOIN user_get_prize ON user_get_prize.id_prize = prize.id_prize WHERE prize.team = ?1 AND user_get_prize.id_user = ?2 ", nativeQuery = true)
    public ArrayList<String> userPrizeQuery(int id_team, int id_user);
    
}
