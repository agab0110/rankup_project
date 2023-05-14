package com.ing.rankup_b.userJoinsTeam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface UserJoinsTeamRepository extends JpaRepository<UserJoinsTeam, UserJoinsTeamKey>{
    @Query(value = "INSERT INTO user_joins_team (id_user, id_team, accepted, points) VALUES (?2, ?1, 1, 0)", nativeQuery = true)
    public ArrayList<String> addMemberQuery(int id_team, int id_user);

    @Query(value = "SELECT user.id_user, user.username, user.photo FROM user WHERE user.username LIKE ?1", nativeQuery = true)
    public ArrayList<String> listUserSearchQuery(String username);

    @Query(value = "SELECT user_joins_team.id_user, user_joins_team.id_team, user_joins_team.date FROM user_team_date WHERE user_joins_team.id_team = ?1", nativeQuery = true)
    public ArrayList<String> listPendingRequestsQuery(int id_team);
}
