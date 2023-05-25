package com.ing.rankup_b.adminReciveNotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("adminReciveNotificationApi/")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8200", "http://localhost:4200"})
public class AdminReciveNotificationController {
    
    @Autowired
    private AdminReciveNotificationService service;

    public AdminReciveNotificationController(AdminReciveNotificationService service) {
        this.service = service;
    }

    /**
     * N.63
     */
    @PostMapping(path = "newNotification/{idAdmin}/{idNotification}")
    public ResponseEntity<?> newNotification(@PathVariable int idAdmin, @PathVariable int idNotification) {
        return this.service.newNotification(idAdmin, idNotification);
    }
}
