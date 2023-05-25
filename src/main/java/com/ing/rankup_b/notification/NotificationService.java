package com.ing.rankup_b.notification;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ing.rankup_b.team.Team;
import com.ing.rankup_b.team.TeamRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private TeamRepository teamRepository;

    public NotificationService(NotificationRepository notificationRepository, TeamRepository teamRepository) {
        this.notificationRepository = notificationRepository;
        this.teamRepository = teamRepository;
    }

    /**
     * Funzione per creare una nuova notifica per un determinato team
     * @param idTeam il team per cui viene creata la notifica
     * @param notification il body della notifica composto solo da title e description
     * @return (200 OK) con la notifica salvata nel body 
     */
    public ResponseEntity<?> newNotification(long idTeam, Notification notification) {
        Team team = this.teamRepository.findById(idTeam).get();
        Date date = new Date();

        notification.setDate(date);
        notification.setTeam(team);

        return ResponseEntity.status(HttpStatus.OK).body(this.notificationRepository.save(notification));
    }
}
