package com.ing.rankup_b.taskCompleted;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskCompletedRepository extends JpaRepository<TaskCompleted, Integer> {

    @Query(value = "SELECT JSON_OBJECT('username',user.username,'name',task.name,'timestamp',task_completed.timestamp,'attached',task_completed.attached) FROM task_completed JOIN task ON task_completed.id_task = task.id_task JOIN user ON user.id_user = task_completed.id_user WHERE task_completed.id_task_completed = ?1", nativeQuery = true)
    String findTask(int idTaskCompleted);

    @Query(value = "UPDATE task_completed SET task_completed.revision_date= ?1, task_completed.status= ?2, task_completed.comment= ?3 WHERE task_completed.id_task_completed = ?4 ", nativeQuery = true)
    String update(Timestamp revision_date, int status, String comment, int idTaskCompleted);

    @Query(value = "SELECT JSON_ARRAYAGG(JSON_OBJECT('id_task', task_completed.id_task_completed, 'name', task.name, 'username', user.name, 'photo', user.photo)) FROM task_completed JOIN task ON task.id_task = task_completed.id_task join user on user.id_user = task_completed.id_user WHERE task_completed.status = 0 and task.id_team = ?1", nativeQuery = true)
    String pending(int id_team);
}
