package com.ing.rankup_b.userReciveNotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("userReciveNotificationApi/")
@CrossOrigin
public class UserReciveNotificationController {
    
    @Autowired
    private UserReciveNotificationService service;

    public UserReciveNotificationController(UserReciveNotificationService service) {
        this.service = service;
    }
    /*
     * N.64
     */
    @GetMapping(path = "/getNotification/{idUser}")
    public ResponseEntity<?> listNotification(@PathVariable int idUser) {
        return this.service.getNotification(idUser);
    }
}
