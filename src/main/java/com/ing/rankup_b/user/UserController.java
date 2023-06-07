package com.ing.rankup_b.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/userApi")
@CrossOrigin(origins = { "http://localhost:8100", "http://localhost:8200", "http://localhost:4200" })
public class UserController {

    @Autowired
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * N.57
     */
    @PostMapping(path = "/signUp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newUser(@Valid @RequestBody User signUpUser) {
        return this.service.newUser(signUpUser);
    }

    /**
     * N.10
     */
    @GetMapping(path = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers() {
        return this.service.getAllUsers();
    }

    /**
     * N.40
     */
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        return this.service.login(loginUser);

    }

    /**
     * N.47
     */
    @PatchMapping(path = "/changeUsername/{idUser}", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeUsername(@PathVariable int idUser, @RequestBody String newUsername) {
        return this.service.changeUsername(idUser, newUsername);
    }

    /**
     * N.46
     */
    @PatchMapping(path = "/changeName/{idUser}", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeName(@PathVariable int idUser, @RequestBody String newName) {
        return this.service.changeName(idUser, newName);
    }

    /*
     * N.51
     */
    @GetMapping(path = "/getUser/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@PathVariable int idUser) {
        String user = this.service.getUser(idUser);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User non trovato");
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    /**
     * N.49
     */
    @PatchMapping(path = "/changeEmail/{idUser}", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeEmail(@PathVariable int idUser, @RequestBody String newEmail) {
        return this.service.changeEmail(idUser, newEmail);
    }

    /**
     * N.48
     */
    @PatchMapping(path = "/changePhoto/{idUser}", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changePhoto(@PathVariable int idUser, @RequestBody String newPhoto) {
        return this.service.changePhoto(idUser, newPhoto);
    }

    /**
     * N.50
     */
    @PatchMapping(path = "/changePassword/{idUser}", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changePassword(@PathVariable int idUser, @RequestBody String newPassword) {
        return this.service.changePassword(idUser, newPassword);
    }

    /**
     * N.10
     */
    @GetMapping(path = "/getNewUsers/{idTeam}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNewUsers(@PathVariable int idTeam) {
        String users = this.service.getNewUsers(idTeam);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
