package com.ing.rankup_b.userJoinsTeam;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ing.rankup_b.user.User;
import com.ing.rankup_b.userJoinsTeam.UserJoinsTeam.Status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> addMember(int id_team, int id_user) {   //GIACENTO
        ArrayList<String> result = this.repository.addMemberQuery(id_team, id_user);
        return result;
    }

    public List<Object> getListUserSearch(String username) {    //GIACENTO

        if (username.isBlank()) {
            return new ArrayList<Object>();
        }

        ArrayList<String> result = this.repository.listUserSearchQuery(username + '%');
        ArrayList<Object> users = new ArrayList<Object>();

        int i = 0;

        for (String r: result) {
            if (i == 20)
                break;

            users.add(new Object() {
                public String id_user = r.split(",")[0];
                public String username = r.split(",")[1];
                public String photo = r.split(",")[2];
            });

            i ++;
        }

        return users;

    }

    public ArrayList<Object> getListPendingRequests(int id_team) {  //GIACENTO
        ArrayList<String> result = this.repository.listPendingRequestsQuery(id_team);

        ArrayList<Object> requests = new ArrayList<Object>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < result.size() - 1; i ++) {
            for (int j = i + 1; j < result.size(); j ++) {
                try {
                    if (sdf.parse(result.get(i).split(",")[2]).compareTo(sdf.parse(result.get(j).split(",")[2])) > 0) {
                        String s = result.get(i);
                        result.set(i, result.get(j));
                        result.set(j, s);
                    }
                } catch (ParseException e) {}
            }
        }

        for (String r: result) {
            requests.add(new Object() {
                public int id_team = Integer.parseInt(r.split(",")[0]);
                public int id_user = Integer.parseInt(r.split(",")[1]);
                public String date = r.split(",")[2].split(" ")[0];
            });
        }

        return requests;

    }

    public ResponseEntity findPartecipants(long idTeam) {
        List<User> partecipants = new ArrayList<>();
        for (UserJoinsTeam u : this.repository.findAll()) {
            if(u.getTeam().getCodice() == idTeam) {
                if (u.getStatus() == Status.Accettato) {
                    partecipants.add(u.getUser());
                }
            }
        }
        if(partecipants.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nessun utente trovato");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(partecipants);
        }
    }
}
