package com.ing.rankup_b.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/taskApi")
@CrossOrigin

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
    public ResponseEntity<?> listTask(@PathVariable long idTeam) {
       return this.service.listTask(idTeam);
    }

    @GetMapping(path = "/userTasks/{idTeam}/{idUser}")
    public ResponseEntity<?> userListTask(@PathVariable long idTeam, @PathVariable int idUser) {
       return this.service.userListTask(idTeam, idUser);
    }

    /*
     * N.60
     */
    @GetMapping(path = "/task/{idTask}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRule(@PathVariable int idTask) {
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
    public ResponseEntity<?> createTask(@RequestBody Task task,@RequestParam("name") String name) {
        return this.service.createTask(task,name);
    }
    @DeleteMapping(path = "/deleteTask/{idTask}/{idTeam}")
    public ResponseEntity<?> deleteTask(@PathVariable int idTask,@PathVariable int idTeam) {
        return this.service.deleteTask(idTask,idTeam);
    }
}
