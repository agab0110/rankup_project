package com.ing.rankup_b.taskForSpecificUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("taskForSpecifiUserApi")
@CrossOrigin
public class TaskForSpecificUserController {
    @Autowired
    private TaskForSpecificUserService service;

    public TaskForSpecificUserController(TaskForSpecificUserService service) {
        this.service = service;
    }
}
