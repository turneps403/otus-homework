package com.otus.homework.my.service;

import com.otus.homework.my.dao.User;
import com.otus.homework.my.events.CreateUserEvent;
import com.otus.homework.my.repositories.UserH2Repositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserH2Service {
    @Autowired
    private UserH2Repositry repo;

    public User createUserFromEvent(CreateUserEvent event) {
        User user = new User(event.userID, event.firstName, event.lastName);
        repo.save(user);
        return user;
    }

    public User selectByID(String userID) {
        User user = repo.findById(userID).orElseThrow(()
                -> new IllegalArgumentException("Invalid user Id:" + userID));
        return user;
    }

    public List<User> selectAll() {
        List<User> list = new ArrayList<>();
        repo.findAll().forEach(list::add);
        return list;
    }
}
