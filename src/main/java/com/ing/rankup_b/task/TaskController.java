package com.ing.rankup_b.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

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
    public ResponseEntity Listtask(@PathVariable int id_team) {
       return this.service.ListTask(id_team);
    }

    @PostMapping(path = "add/task", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addTask(@RequestBody Map<String,Object> body) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.service.addTask(
                        body.get("task_name").toString(), Integer.parseInt(body.get("points").toString()), body.get("description").toString(), body.get("end_date").toString(), Integer.parseInt(body.get("id_team").toString()), Integer.parseInt(body.get("id_admin").toString())
                )
        );
    }

    @GetMapping(path = "checkUsername")
    public ResponseEntity checkUsername(@RequestParam("username") String username, @RequestParam("id_team") int id_team) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.service.getCheckUsername(
                        username, id_team
                )
        );
    }

    @PostMapping(path = "add/specificTasks", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addSpecificTasks(@RequestBody Map<String, ArrayList<Integer>> body) {

        return ResponseEntity.status(HttpStatus.OK).body(
                this.service.addSpecificTasks(body.get("users"), body.get("id_task"))
        );
    }
}
