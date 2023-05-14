package com.ing.rankup_b.taskCompleted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("taskCompletedApi")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8200", "http://localhost:4200"})

public class TaskCompletedController {
    
    @Autowired
    private TaskCompletedService service;

    public TaskCompletedController(TaskCompletedService service) {
        this.service = service;
    }
}
