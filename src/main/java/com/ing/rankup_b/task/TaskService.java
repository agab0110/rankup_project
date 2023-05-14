package com.ing.rankup_b.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }
    public ResponseEntity ListTask(int Codice){
        List <Task> tasks = new ArrayList<>();

        for (Task task : (List<Task>)this.repository.findAll()) {
            if (task.getTeam().getCodice() == Codice) {
                tasks.add(task);
            }
        }
        if (tasks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Non ci sono task per questo team");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }
}
