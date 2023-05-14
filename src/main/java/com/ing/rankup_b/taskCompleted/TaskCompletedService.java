package com.ing.rankup_b.taskCompleted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskCompletedService {
    
    @Autowired
    private TaskCompletedRepository repository;

    public TaskCompletedService(TaskCompletedRepository repository) {
        this.repository = repository;
    }

    public String researchTask(int idTaskompletata) {
        return this.repository.findTask(idTaskompletata);
    }
}
