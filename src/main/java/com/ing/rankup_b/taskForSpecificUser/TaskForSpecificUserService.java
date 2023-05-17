package com.ing.rankup_b.taskForSpecificUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskForSpecificUserService {
    @Autowired
    private TaskForSpecificUserRepository repository;

    public TaskForSpecificUserService(TaskForSpecificUserRepository repository) {
        this.repository = repository;
    }
}
