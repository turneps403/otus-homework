package org.otus.hw07.controllers;

import org.otus.hw07.entities.User;
import org.otus.hw07.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/{userID}")
    public ResponseEntity<User> readUser(@PathVariable Integer userID) {
        User user = userRepo.read(userID);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User req_user) {
        User user = userRepo.create(req_user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{userID}")
    public ResponseEntity<User> updateUser(@PathVariable Integer userID, @RequestBody User req_user) {
        User user = userRepo.update(userID, req_user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<Map<String, String>> updateUser(@PathVariable Integer userID) {
        userRepo.delete(userID);
        Map resp = Collections.singletonMap("deleted", "ok");
        return new ResponseEntity<>(resp, HttpStatus.NO_CONTENT);
    }
}
