package com.ing.rankup_b.adminManageTeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * N.13
     */
    @PostMapping(path = "/addAdmin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAdmin(@RequestParam("idTeam") long idTeam, @RequestParam("idUser") int idUser) {
        return service.addAdmin(idTeam,idUser);
    }

}
