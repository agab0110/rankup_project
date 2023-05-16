package com.ing.rankup_b.taskCompleted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("taskCompletedApi")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8200", "http://localhost:4200"})

public class TaskCompletedController {
    
    @Autowired
    private TaskCompletedService service;

    public TaskCompletedController(TaskCompletedService service) {
        this.service = service;
    }

    @GetMapping (path = "/request/{idTaskCompletata}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTaskDelivered(@PathVariable int idTaskCompletata) {
        String taskCompleted = this.service.researchTask(idTaskCompletata);

        if (taskCompleted == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task non trovata");

        return ResponseEntity.status(HttpStatus.OK).body(taskCompleted);
    }

    @GetMapping(path ="/taskAccepted/{id_team}")
    public ResponseEntity taskAccepted(@PathVariable int id_team){
        return this.service.taskAccepted(id_team);
    }

    @GetMapping(path ="/taskrejected/{id_team}")
    public ResponseEntity taskrejected(@PathVariable int id_team){
        return this.service.taskRefused(id_team);
    }

    /*
     * N.61
     * AN
     */
    @PostMapping(path = "/taskCompleted", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskCompleted insert(@Valid @RequestBody TaskCompleted taskCompleted) {
        return this.service.insert(taskCompleted);
    }
}
