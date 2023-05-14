package com.ing.rankup_b.adminManageTeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adminManageTeamApi")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8200", "http://localhost:4200"})
public class AdminManageTeamController {
    
    @Autowired
    private AdminManageTeamService service;

    public AdminManageTeamController(AdminManageTeamService service) {
        this.service = service;
    }
}
