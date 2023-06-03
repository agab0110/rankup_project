package com.ing.rankup_b.taskCompleted;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskCompletedRepository extends JpaRepository<TaskCompleted, Integer> {

    @Query(value = "SELECT JSON_OBJECT('username',user.username,'name',task.name,'timestamp',task_completed.timestamp,'attached',task_completed.attached) FROM task_completed JOIN task ON task_completed.id_task = task.id_task JOIN user ON user.id_user = task_completed.id_user WHERE task_completed.id_task_completed = ?1", nativeQuery = true)
    String findTask(int idTaskCompleted);

    @Query(value = "UPDATE task_completed SET task_completed.revision_date= ?1, task_completed.status= ?2, task_completed.comment= ?3, task_completed.bonus_points= ?4 WHERE task_completed.id_task_completed = ?5 ", nativeQuery = true)
    String update(Timestamp revision_date, int status, String comment, int bonus, int idTaskCompleted);

    @Query(value = "SELECT task.points FROM task WHERE task.id_task = ?1", nativeQuery = true)
    int getTaskPoints(int id_task);

    @Query(value = "UPDATE user_joins_team SET user_joins_team.points = user_joins_team.points + ?1 WHERE user_joins_team.id_user = ?2 AND user_joins_team.id_team = ?3", nativeQuery = true)
    String updatePoints(int punti, int idUser, long idTeam);

    @Query(value = "SELECT JSON_ARRAYAGG(JSON_OBJECT('id_task_completed', task_completed.id_task_completed, 'name', task.name, 'username', user.username, 'photo', user.photo, 'id_task', task_completed.id_task)) FROM task_completed JOIN task ON task.id_task = task_completed.id_task join user on user.id_user = task_completed.id_user WHERE task_completed.status = 0 and task.id_team = ?1", nativeQuery = true)
    String pending(int id_team);
}
