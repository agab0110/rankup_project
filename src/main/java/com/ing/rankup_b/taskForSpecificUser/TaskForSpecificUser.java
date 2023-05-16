package com.ing.rankup_b.taskForSpecificUser;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ing.rankup_b.task.Task;
import com.ing.rankup_b.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "task_for_specific_user")
public class TaskForSpecificUser {
    @EmbeddedId
    @Column(name = "key")
    TaskForSpecificUserKey key;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @MapsId("idUser")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_task")
    @MapsId("idTask")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Task task;
}
