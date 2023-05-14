package com.ing.rankup_b.adminManageTeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/adminManageTeamApi")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8200", "http://localhost:4200"})
public class AdminManageTeamController {
    
    @Autowired
    private AdminManageTeamService service;

    public AdminManageTeamController(AdminManageTeamService service) {
        this.service = service;
    }

    @PostMapping(path = "addAdmin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addAdmin(@RequestBody Map<String,Object> body) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.service.addAdmin(
                        Integer.parseInt(body.get("id_team").toString()), Integer.parseInt(body.get("id_user").toString())
                )
        );
    }

}
