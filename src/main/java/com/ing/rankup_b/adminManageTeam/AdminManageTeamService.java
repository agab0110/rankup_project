package com.ing.rankup_b.adminManageTeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminManageTeamService {
    
    @Autowired
    private AdminManageTeamRepository repository;

    public AdminManageTeamService(AdminManageTeamRepository repository) {
        this.repository = repository;
    }

    public List<String> addAdmin(int id_team, int id_user) {
        ArrayList<String> result = this.repository.addMemberQuery(id_team, id_user);
        ArrayList<String> result2 = this.repository.addAdminQuery(id_team, id_user);
        return result2;
    }

}
