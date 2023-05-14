package com.ing.rankup_b.userJoinsTeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserJoinsTeamService {
    
    @Autowired
    private UserJoinsTeamRepository repository;

    public UserJoinsTeamService(UserJoinsTeamRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity deleteUserRequest(Long teamCode, int userId) {
        for (UserJoinsTeam userJoinsTeam : this.repository.findAll()) {
            if (userJoinsTeam.getTeam().getCodice() == teamCode) {
                if (userJoinsTeam.getUser().getId() == userId) {
                    this.repository.delete(userJoinsTeam);
                    return ResponseEntity.status(HttpStatus.OK).body("Richiesta eliminata");
                }
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erorre imprevisto");
    }
}
