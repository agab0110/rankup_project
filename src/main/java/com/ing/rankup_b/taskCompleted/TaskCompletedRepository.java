package com.ing.rankup_b.taskCompleted;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskCompletedRepository extends JpaRepository<TaskCompleted, Integer> {

    @Query(value = "SELECT JSON_OBJECT('username',user.username,'name',task.name,'timestamp',task_completed.timestamp,'attached',task_completed.attached) FROM task_completed JOIN task ON task_completed.task_id_task = task.id_task JOIN user ON user.id_user = task_completed.id_user WHERE task_completed.id_task_completed = ?1", nativeQuery = true)
    String findTask(int idTaskompletata);
}
