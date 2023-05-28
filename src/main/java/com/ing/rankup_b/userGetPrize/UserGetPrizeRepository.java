package com.ing.rankup_b.userGetPrize;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserGetPrizeRepository extends JpaRepository<UserGetPrize, UserGetPrizeKey> {
    @Query(value = "INSERT INTO user_get_prize(id_prize,id_user,date) VALUES (?1,?2,NULL)", nativeQuery = true)
    public String addPrize(int id_price, int id_user);
}
