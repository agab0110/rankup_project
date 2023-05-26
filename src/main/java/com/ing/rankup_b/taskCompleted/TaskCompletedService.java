package com.ing.rankup_b.taskCompleted;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ing.rankup_b.ruleCompleted.RuleCompleted;
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

        for (TaskCompleted task : this.repository.findAll()) {
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

        for (TaskCompleted task : this.repository.findAll()) {
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
    public String confirmation(int idTaskCompleted, int status, RuleCompleted ruleCompleted) {
        Timestamp revisionDate = Timestamp.from(Instant.now());

        return this.repository.update(revisionDate, status, ruleCompleted.getComment(), idTaskCompleted);
    }
  
    /**
     * Funzione per ricercare tutte le task completate da un utente in un determinato team
     * @param idTeam il team in cui si deve effettuare la ricerca
     * @param idUser l'utente per cui si deve effettuare la ricerca
     * @return (400 BAD_REQUEST) se non viene trovato nulla <br>(200 OK) con la lista di task altrimenti
     */
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

    /*
     * N.39
     */
    public ResponseEntity getTaskCompletedDetails(int idTaskCompleted) {
        return ResponseEntity.status(HttpStatus.OK).body(this.repository.findById(idTaskCompleted));
    }
}
