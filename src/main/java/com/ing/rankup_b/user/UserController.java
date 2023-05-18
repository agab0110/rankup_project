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

import java.util.List;

@RestController
@RequestMapping("/userApi")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8200", "http://localhost/4200"})
public class UserController {

    @Autowired
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(path = "/signUp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity newUser(@Valid @RequestBody User signUpUser) {
        return this.service.newUser(signUpUser);
    }

    @GetMapping(path = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return this.service.getAllUsers();
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody User loginUser) {
        return this.service.login(loginUser);
    }

    @PatchMapping(path = "/changeName/{id_user}", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changeName(@PathVariable int id_user, @RequestBody String newName) {
        return this.service.changeName(id_user, newName);
    }

    /*
     * N.51
     */
    @GetMapping(path = "/getUser/{id_user}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(@PathVariable int id_user) {
        String user = this.service.getUser(id_user);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User non trovato");
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
