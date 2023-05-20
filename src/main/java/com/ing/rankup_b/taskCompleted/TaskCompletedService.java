package com.ing.rankup_b.taskCompleted;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ing.rankup_b.taskCompleted.TaskCompleted.Status;

import jakarta.validation.Valid;

@Service
public class TaskCompletedService {
    
    @Autowired
    private TaskCompletedRepository repository;

    public TaskCompletedService(TaskCompletedRepository repository) {
        this.repository = repository;
    }

    public String researchTask(int idTaskompletata) {
        return this.repository.findTask(idTaskompletata);
    }
  
    public ResponseEntity taskAccepted(int Codice){
        List<TaskCompleted> acceptedtask = new ArrayList<>();

        for (TaskCompleted task : (List<TaskCompleted>)this.repository.findAll()) {
            if (task.getTask().getTeam().getCodice() == Codice) {
                if (task.getStatus() == Status.Accettato) {
                    acceptedtask.add(task);
                }
            }
            
        }
        
        if(acceptedtask.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nessun task completato");
        }
        return ResponseEntity.status(HttpStatus.OK).body(acceptedtask);
    }

    public ResponseEntity taskRefused(int Codice){
        List<TaskCompleted> refusedtask = new ArrayList<>();

        for (TaskCompleted task : (List<TaskCompleted>)this.repository.findAll()) {
            if (task.getTask().getTeam().getCodice() == Codice) {
                if (task.getStatus() == Status.Rifiutato) {
                    refusedtask.add(task);
                }
            }
            
        }
        if(refusedtask.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nessun task completato");
        }
        return ResponseEntity.status(HttpStatus.OK).body(refusedtask);
    }

    /*
     * N.25
     */
    public String getPending(int id_team) {
        return this.repository.pending(id_team);
    }

    /*
     * N.33
     */
    public String confirmation(int id_task_completed, int status, String comment) {
        Timestamp revision_date = Timestamp.from(Instant.now());
        return this.repository.update(revision_date, status, comment, id_task_completed);
    }
}
