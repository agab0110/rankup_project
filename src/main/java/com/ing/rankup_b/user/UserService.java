package com.ing.rankup_b.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Funzione per l'inserimento di un nuovo utente
     * 
     * @param user l'utente da inserire
     * @return (400 BAD_REQUEST) se l'username è duplicato, (200 OK) altrimenti
     */
    public ResponseEntity newUser(User signUpUser) {
        for (User user : (List<User>)this.repository.findAll()) {
            if (user.getUsername().equals(signUpUser.getUsername())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username duplicato");
            }
        }
        this.repository.save(signUpUser);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * Funzione per prendere tutti gli utenti salvati
     * @return la lista degli utenti
     */
    public List<User> getAllUsers() {
        return (List<User>) this.repository.findAll();
    }

    /**
     * Funzione per effetuare la login
     * 
     * @param loginUser contine username e password da controllare
     * @return (200 OK) e user se i controlli vanno a buon fine, (400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity login(User loginUser) {
        for (User user : (List<User>)this.repository.findAll()) {
            if (user.getUsername().equals(loginUser.getUsername()) && user.getPassword().equals(loginUser.getPassword())) {
                return ResponseEntity.status(HttpStatus.OK).body(user);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username o password errati");
    }
}
