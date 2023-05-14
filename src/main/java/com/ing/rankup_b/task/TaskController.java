package com.ing.rankup_b.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("taskApi")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8200", "http://localhost:4200"})

public class TaskController {
    
    @Autowired
    private TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping(path = "/tasks/{id_team}")
    public ResponseEntity ListRule(@PathVariable int id_team) {
       return this.service.ListTask(id_team);
        
    }
}
