package com.ing.rankup_b.taskCompleted;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ing.rankup_b.task.Task;
import com.ing.rankup_b.taskCompleted.TaskCompleted.Status;

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

    public ResponseEntity getTaskForASpecificUser(long idTeam, int idUser) {
        List<Task> tasks = new ArrayList<>();

        for (TaskCompleted t : this.repository.findAll()) {
            if (t.getTask().getTeam().getCodice() == idTeam && t.getUser().getId() == idUser && t.getStatus() == Status.Accettato) {
                tasks.add(t.getTask());
            }
        }

        if (tasks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nessun task trovato per questo utente");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(tasks);
        }
    }
}
