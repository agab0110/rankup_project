package com.ing.rankup_b.userJoinsTeam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface UserJoinsTeamRepository extends JpaRepository<UserJoinsTeam, Integer>{
    @Query(value = "SELECT user.id_user, user.username, user.photo FROM user WHERE user.username LIKE ?1", nativeQuery = true)
    public ArrayList<String> listUserSearchQuery(String username);

    @Query(value = "INSERT INTO user_joins_team(points,accepted,id_team,id_user) VALUES (?1,?2,?3,?4)", nativeQuery = true)
    public ArrayList<String> addUserQuery(int points,int accepted,long id_team, int id_user);
    
    @Query(value = "SELECT user.id_user, user.username, user.photo, user_joins_team.points FROM user JOIN user_joins_team ON user_joins_team.id_user = user.id_user  WHERE user_joins_team.id_team = ?1 AND user.username LIKE ?2", nativeQuery = true)
    public ArrayList<String> userJoinsTeamSearch(int idTeam, String username);
}
