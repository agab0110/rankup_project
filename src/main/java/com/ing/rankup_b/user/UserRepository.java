package com.ing.rankup_b.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT JSON_OBJECT('name', user.name, 'surname',  user.surname, 'username', user.username, 'email', user.email, 'photo', user.photo) FROM user WHERE user.id_user = ?1", nativeQuery = true)
    public String findUser(int id_user);

}
