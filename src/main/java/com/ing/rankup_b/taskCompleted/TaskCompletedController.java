package com.ing.rankup_b.taskCompleted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/taskCompletedApi")
@CrossOrigin(origins = { "http://localhost:8100", "http://localhost:8200", "http://localhost:4200" })

public class TaskCompletedController {

    @Autowired
    private TaskCompletedService service;

    public TaskCompletedController(TaskCompletedService service) {
        this.service = service;
    }

    /**
     * 31
     */
    @GetMapping (path = "/request/{idTaskCompletata}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTaskDelivered(@PathVariable int idTaskCompletata) {
        String taskCompleted = this.service.researchTask(idTaskCompletata);

        if (taskCompleted == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task non trovata");

        return ResponseEntity.status(HttpStatus.OK).body(taskCompleted);
    }

    /**
     * N.18
     */
    @GetMapping(path ="/taskAccepted/{id_team}")
    public ResponseEntity<?> taskAccepted(@PathVariable int id_team){
        return this.service.taskAccepted(id_team);
    }

    /**
     * N.18
     */
    @GetMapping(path ="/taskRejected/{id_team}")
    public ResponseEntity<?> taskRejected(@PathVariable int id_team){
        return this.service.taskRefused(id_team);
    }

    /**
     * N.25
     */
    @GetMapping(path = "/pending/{id_team}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPending(@PathVariable int id_team) {
        return this.service.getPending(id_team);
    }

    /**
     * N.33
     */
    @PatchMapping(path = "/confirmation/{idTaskCompleted}/{status}")
    public ResponseEntity<?> confirmation(@PathVariable int idTaskCompleted, @PathVariable int status,
            @RequestBody TaskCompleted taskCompleted) {
        this.service.confirmation(idTaskCompleted, status, taskCompleted);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * N.36
     * N.24
     */
    @GetMapping(path = "/getTaskForSpecificUser/{idTeam}/{idUser}")
    public ResponseEntity<?> getTaskForSpecificUser(@PathVariable long idTeam, @PathVariable int idUser) {
        return this.service.getTaskForASpecificUser(idTeam, idUser);
    }

    /*
     * N.39
     * N.67
     */
    @GetMapping(path = "/taskCompletedDetails/{idTaskCompleted}")
    public ResponseEntity taskCompletedDetails(@PathVariable int idTaskCompleted) {
        return this.service.getTaskCompletedDetails(idTaskCompleted);
    }
    
    /*
     * N.61
     */
    @PostMapping(path = "/taskCompleted", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskCompleted insert(@Valid @RequestBody TaskCompleted taskCompleted) {
        return this.service.insert(taskCompleted);
    }
}
