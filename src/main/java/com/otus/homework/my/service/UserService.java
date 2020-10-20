package com.otus.homework.my.service;

import com.otus.homework.my.domain.User;
import com.otus.homework.my.exceptions.MyBadRequestException;
import com.otus.homework.my.exceptions.MyResourceNotFoundException;

public interface UserService {
    User lookupByEmail(String email) throws MyResourceNotFoundException;
    User lookupById(Integer userId) throws MyResourceNotFoundException;
    User createUser(String fristName, String lastName, String email, String phone, String password) throws MyBadRequestException;
    User createUser(User user) throws MyBadRequestException;
    void updateUser(Integer userId, User user) throws MyBadRequestException;
    void removeUser(Integer userId) throws MyResourceNotFoundException;

}
