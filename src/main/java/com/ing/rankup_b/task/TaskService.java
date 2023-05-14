package com.ing.rankup_b.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }
}
