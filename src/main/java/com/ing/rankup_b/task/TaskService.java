package com.ing.rankup_b.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }


    public ArrayList<String> addTask(String task_name, int points, String description, String end_date, int id_team, int id_admin) {
        ArrayList<String> result = this.repository.addTaskQuery(task_name, points,  description, end_date, id_team, id_admin);
        return result;
    }

    public ArrayList<String> getCheckUsername(String username, int id_team) {
        ArrayList<String> result = this.repository.checkUsernameQuery(username, id_team);
        return result;
    }

    public ArrayList<String> addSpecificTasks(ArrayList<Integer> users, ArrayList<Integer> id_task) {
        ArrayList<String> result = new ArrayList<String>();
        for (Integer user: users) {
            result = this.repository.addSpecificTasksQuery(user, id_task.get(0));
        }
        return result;
    }
}
