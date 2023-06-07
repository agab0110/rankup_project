package com.ing.rankup_b.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ing.rankup_b.team.Team;
import com.ing.rankup_b.team.TeamRepository;
import com.ing.rankup_b.taskCompleted.TaskCompleted;
import com.ing.rankup_b.taskCompleted.TaskCompletedRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TaskCompletedRepository taskCompletedRepository;

    public TaskService(TaskRepository repository, TeamRepository teamRepository,
            TaskCompletedRepository taskCompletedRepository) {
        this.taskRepository = repository;
        this.teamRepository = teamRepository;
        this.taskCompletedRepository = taskCompletedRepository;
    }

    public ResponseEntity<?> listTask(Long codice) {
        List<Task> tasks = new ArrayList<>();
        List<Task> removingTasks = new ArrayList<>();

        Date currentDate = new Date();

        for (Task task : this.taskRepository.findAll()) {
            if (task.getTeam().getCodice() == codice) {
                if (task.getEndDate().compareTo(currentDate) >= 0)
                tasks.add(task);
            }
        }
        
        for (TaskCompleted taskCompleted : this.taskCompletedRepository.findAll()) {
            for (Task task : this.taskRepository.findAll()) {
                if (taskCompleted.getTask() == task) {
                    removingTasks.add(task);
                }
            }
        }

        tasks.removeAll(removingTasks);

        if (tasks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Non ci sono task per questo team");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    public ResponseEntity<?> userListTask(Long codice, int idUser){
        List<Task> userTasks = new ArrayList<>();
        List<Task> removingTasks = new ArrayList<>();


        for (Task task : this.taskRepository.findAll()) {
            if(task.getTeam().getCodice() == codice){
                userTasks.add(task);
            }
        }

        for(TaskCompleted taskCompleted : this.taskCompletedRepository.findAll()){
            for(Task uTask : userTasks){
                if(taskCompleted.getTask().getId() == uTask.getId() && taskCompleted.getUser().getId() == idUser){
                    removingTasks.add(uTask);
                }
            }
        }

        userTasks.removeAll(removingTasks);

        if(userTasks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Non ci sono task per questo team");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(userTasks);
        }
    }

    

    public ArrayList<String> addTask(String task_name, int points, String description, String end_date, int id_team,
            int id_admin) {
        ArrayList<String> result = this.taskRepository.addTaskQuery(task_name, points, description, end_date, id_team,
                id_admin);
        return result;
    }

    public ArrayList<String> getCheckUsername(String username, int id_team) {
        ArrayList<String> result = this.taskRepository.checkUsernameQuery(username, id_team);
        return result;
    }

    public ArrayList<String> addSpecificTasks(ArrayList<Integer> users, ArrayList<Integer> id_task) {
        ArrayList<String> result = new ArrayList<String>();
        for (Integer user : users) {
            result = this.taskRepository.addSpecificTasksQuery(user, id_task.get(0));
        }
        return result;
    }

    /*
     * N.60
     */
    public String getTask(int idTask) {
        return this.taskRepository.findTask(idTask);
    }

    /*
     * N.17
     */
    public ResponseEntity<?> createTask(Task task, String name) {
        for (Team t : this.teamRepository.findAll()) {
            if (task.getTeam().getCodice() == t.getCodice()) {
                for (Task r : t.getTasks()) {
                    if (r.getName().equals(name)) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome duplicato");
                    }
                }
            }
        }
        Date date = new Date();
        task.setStartDate(date);
        this.taskRepository.save(task);

        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    public ResponseEntity<?> deleteTask(int idTask,long idTeam) {
        for (Task t : this.taskRepository.findAll()) {
            if (t.getId() == idTask) {
                if (t.getTeam().getCodice() == idTeam) {
                    this.taskRepository.delete(t);
                    return ResponseEntity.status(HttpStatus.OK).body(null);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task non trovato");
    }
}
