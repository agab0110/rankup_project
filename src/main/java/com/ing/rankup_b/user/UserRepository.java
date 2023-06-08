package com.ing.rankup_b.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT JSON_OBJECT('name', user.name, 'surname',  user.surname, 'username', user.username, 'email', user.email, 'photo', user.photo) FROM user WHERE user.id_user = ?1", nativeQuery = true)
    public String findUser(int id_user);

    @Query(value = "SELECT JSON_ARRAYAGG(JSON_OBJECT('idUser', user.id_user, 'username', user.username, 'photo', user.photo)) FROM user WHERE user.id_user NOT IN (SELECT admin_manage_team.id_user FROM admin_manage_team WHERE admin_manage_team.id_team = ?1) AND user.id_user NOT IN (SELECT user_joins_team.id_user FROM user_joins_team WHERE user_joins_team.id_team = ?1);", nativeQuery = true)
    public String getNewUsers(int idTeam);
}
