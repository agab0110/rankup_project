package com.ing.rankup_b.adminManageTeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminManageTeamService {
    
    @Autowired
    private AdminManageTeamRepository repository;

    public AdminManageTeamService(AdminManageTeamRepository repository) {
        this.repository = repository;
    }
}
