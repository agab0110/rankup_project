package com.ing.rankup_b.adminManageTeam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface AdminManageTeamRepository extends JpaRepository<AdminManageTeam, Integer> {

    @Query(value = "INSERT INTO user_joins_team (id_user, id_team, accepted, points) VALUES (?2, ?1, 1, 0)", nativeQuery = true)
    public ArrayList<String> addMemberQuery(int id_team, int id_user);

    @Query(value = "INSERT INTO admin_manage_team (id_team, id_user) VALUES (?1, ?2)", nativeQuery = true)
    public ArrayList<String> addAdminQuery(int id_team, int id_user);



}
