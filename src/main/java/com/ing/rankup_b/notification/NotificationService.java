package com.ing.rankup_b.notification;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ing.rankup_b.team.Team;
import com.ing.rankup_b.team.TeamRepository;
import com.ing.rankup_b.user.User;
import com.ing.rankup_b.user.UserRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    private UserRepository userRepository;
    private TeamRepository teamRepository;

    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository, TeamRepository teamRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }

    public ResponseEntity<?> newNotification(long idTeam, int idUser, Notification notification) {
        User user = new User();
        user = this.userRepository.findById(idUser).get();
        
        Team team = new Team();
        team = this.teamRepository.findById(idTeam).get();

        Date date = new Date();

        notification.setDate(date);
        notification.setUser(user);
        notification.setTeam(team);

        return ResponseEntity.status(HttpStatus.OK).body(this.notificationRepository.save(notification));
    }
}
