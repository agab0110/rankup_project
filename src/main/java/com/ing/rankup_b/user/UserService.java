package com.ing.rankup_b.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public ResponseEntity<?> newUser(User signUpUser) {
        for (User user : this.repository.findAll()) {
            if (user.getUsername().equals(signUpUser.getUsername())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username duplicato");
            }
            if (user.getEmail().equals(signUpUser.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email duplicata");
            }
        }
        this.repository.save(signUpUser);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * Funzione per prendere tutti gli utenti salvati
     * 
     * @return la lista degli utenti
     */
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(this.repository.findAll());
    }

    /**
     * Funzione per effetuare la login
     * 
     * @param loginUser contiene username e password da controllare
     * @return (200 OK) e user se i controlli vanno a buon fine, (400 BAD_REQUEST)
     *         altrimenti
     */
    public ResponseEntity<?> login(User loginUser) {
        for (User user : this.repository.findAll()) {
            if (user.getUsername().equals(loginUser.getUsername())
                    && user.getPassword().equals(loginUser.getPassword())) {
                return ResponseEntity.status(HttpStatus.OK).body(user);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username o password errati");
    }

    /**
     * Funzione per cambiare lo username dell'utente
     * 
     * @param idUser      id dell'utente a cui cambiare lo username
     * @param newUsername nuovo nome dell'utente
     * @return (200 OK) e user se i controlli vanno a buon fine, (400 BAD_REQUEST)
     *         altrimenti
     */
    public ResponseEntity<?> changeUsername(int idUser, String newUsername) {
        User user = this.repository.findById(idUser).get();

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Utente non trovato");
        }

        for (User u : this.repository.findAll()) {
            if (u.getUsername().equals(newUsername)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username duplicato");
            }
        }

        user.setUsername(newUsername);
        this.repository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    /**
     * Funzione per cambiare il nome dell'utente
     * 
     * @param idUser  id dell'utente a cui cambiare il nome
     * @param newName nuovo nome dell'utente
     * @return (200 OK) e user se i controlli vanno a buon fine, (400 BAD_REQUEST)
     *         altrimenti
     */
    public ResponseEntity<?> changeName(int idUser, String newName) {
        User user = this.repository.findById(idUser).get();

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Utente non trovato");
        }

        user.setName(newName);
        this.repository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    /*
     * N.51
     */
    public String getUser(int idUser) {
        return this.repository.findUser(idUser);
    }

    /**
     * Funzione per cambiare l'email dell'utente
     * 
     * @param idUser   id dell'utente a cui cambiare l'email
     * @param newEmail nuova email dell'utente
     * @return (200 OK) e user se i controlli vanno a buon fine, (400 BAD_REQUEST)
     *         altrimenti
     */
    public ResponseEntity<?> changeEmail(int idUser, String newEmail) {
        User user = this.repository.findById(idUser).get();

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Utente non trovato");
        }

        for (User u : this.repository.findAll()) {
            if (u.getEmail().equals(newEmail)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email duplicata");
            }
        }

        user.setEmail(newEmail);
        this.repository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    /**
     * Funzione per cambiare la foto dell'utente
     * 
     * @param idUser l'id dell'utente a cui cambiare la foto
     * @param newPhoto l'url della nuova foto dell'utente
     * @return (200 OK) e user se i controlli vanno a buon fine, (400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity<?> changePhoto(int idUser, String newPhoto) {
        for (User user : this.repository.findAll()) {
            if (user.getId() == idUser) {
                user.setPhoto(newPhoto);
                this.repository.save(user);
                return ResponseEntity.status(HttpStatus.OK).body(user);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Utente non trovato");
    }

    /**
     * Funzione per cambiare la password dell'utente
     * 
     * @param idUser   id dell'utente a cui cambiare la password
     * @param newPassword nuova password dell'utente
     * @return (200 OK) e user se i controlli vanno a buon fine, (400 BAD_REQUEST)
     *         altrimenti
     */
    public ResponseEntity<?> changePassword(int idUser, String newPassword) {
        for (User user : this.repository.findAll()) {
            if (user.getId() == idUser) {
                user.setPassword(newPassword);
                this.repository.save(user);
                return ResponseEntity.status(HttpStatus.OK).body(user);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Utente non trovato");
    }

    /*
     * N
     */
    public String getNewUsers(int idTeam) {
        return this.repository.getNewUsers(idTeam);
    }
}
