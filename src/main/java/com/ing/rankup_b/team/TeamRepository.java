package com.ing.rankup_b.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query(value = "SELECT JSON_ARRAYAGG(JSON_OBJECT('idTeam', team.id_team, 'name', team.name, 'photo', team.photo)) FROM team WHERE team.privacy = 1 AND team.name LIKE ?1%", nativeQuery = true)
    String findByName(String title);

    @Query(value = "SELECT JSON_ARRAYAGG(JSON_OBJECT('idTeam', team.id_team, 'name:', team.name, 'photo', team.photo)) FROM team WHERE team.privacy = 1 ORDER BY RAND() LIMIT 20;", nativeQuery = true)
    String rand();

}
