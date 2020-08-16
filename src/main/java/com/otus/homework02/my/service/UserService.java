package com.otus.homework02.my.service;

import com.otus.homework02.my.domain.User;
import com.otus.homework02.my.exceptions.MyBadRequestException;
import com.otus.homework02.my.exceptions.MyResourceNotFoundException;

public interface UserService {

    User lookupById(Integer userId) throws MyResourceNotFoundException;
    User createUser(String fristName, String lastName, String email, String phone) throws MyBadRequestException;
    User createUser(User user) throws MyBadRequestException;
    void updateUser(Integer userId, User user) throws MyBadRequestException;
    void removeUser(Integer userId) throws MyResourceNotFoundException;

}
