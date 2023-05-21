package com.ing.rankup_b.userJoinsTeam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface UserJoinsTeamRepository extends JpaRepository<UserJoinsTeam, UserJoinsTeamKey>{
    @Query(value = "SELECT user.id_user, user.username, user.photo FROM user WHERE user.username LIKE ?1", nativeQuery = true)
    public ArrayList<String> listUserSearchQuery(String username);
}
