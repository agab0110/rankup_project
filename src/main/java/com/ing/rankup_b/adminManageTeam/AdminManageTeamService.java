package com.ing.rankup_b.adminManageTeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ing.rankup_b.team.Team;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminManageTeamService {
    
    @Autowired
    private AdminManageTeamRepository repository;

    public AdminManageTeamService(AdminManageTeamRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity addAdmin(AdminManageTeam manageTeam) {
        this.repository.save(manageTeam);
        return ResponseEntity.status(HttpStatus.OK).body("admin aggiunto con sucesso");
        
    }

}
