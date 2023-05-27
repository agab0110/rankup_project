package com.ing.rankup_b.adminReciveNotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminReciveNotificationService {

    @Autowired
    private AdminReciveNotificationRepository repository;

    public AdminReciveNotificationService(AdminReciveNotificationRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity getAdminNotification(int idAdmin){
        List<AdminReciveNotification> adminNotification = new ArrayList<>();
        for(AdminReciveNotification n : this.repository.findAll()){
            if(n.getAdmin().getId() == idAdmin){
                adminNotification.add(n);
            }
        }
        if(!adminNotification.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(adminNotification);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nessuna notifica presente");
        }
    }
}
