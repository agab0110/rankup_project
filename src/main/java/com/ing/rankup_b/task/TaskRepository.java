package com.ing.rankup_b.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface TaskRepository extends JpaRepository<Task, Integer>{

    @Query(value = "INSERT INTO task (description, end_date, name, points, admin, id_team) VALUES (?3, ?4, ?1, ?2, ?6, ?5)", nativeQuery = true)
    public ArrayList<String> addTaskQuery(String task_name, int points, String description, String end_date, int id_team, int id_admin);

    @Query(value = "SELECT user.id_user FROM user JOIN user_joins_team ON user.id_user = user_joins_team.id_user WHERE user.username = ?1 AND user_joins_team.id_team = ?2", nativeQuery = true)
    public ArrayList<String> checkUsernameQuery(String username, int id_team);

    @Query(value = "INSERT INTO task_for_specific_user (id_task, user) VALUES (?2, ?1)", nativeQuery = true)
    public ArrayList<String> addSpecificTasksQuery(Integer user, int id_task);

    
    /*
     * N.60
     * AN
     */
    @Query(value = "SELECT JSON_OBJECT('taskName',task.name,'points',task.points,'description',task.description) FROM task WHERE task.id_task = ?1", nativeQuery = true)
    String findTask(int idTask);
}
