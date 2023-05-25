package com.ing.rankup_b.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/taskApi")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8200", "http://localhost:4200"})

public class TaskController {

    @Autowired
    private TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    /**
     * N.28
     * N.56
     */
    @GetMapping(path = "/tasks/{idTeam}")
    public ResponseEntity listTask(@PathVariable long idTeam) {
       return this.service.listTask(idTeam);
    }

    /*
     * N.60
     */
    @GetMapping(path = "/task/{idTask}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRule(@PathVariable int idTask) {
        String task = this.service.getTask(idTask);
        
        if (task == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task non trovata");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getTask(idTask));
    }

    /*
    * N.17 
    */
    @PostMapping(path = "/createTask", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        return this.service.createTask(task);
    }
}
