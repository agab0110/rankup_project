package com.ing.rankup_b.taskForSpecificUser;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class TaskForSpecificUserKey {
    @Column(name = "user")
    private int idUser;

    @Column(name = "task")
    private int idTask;
}
