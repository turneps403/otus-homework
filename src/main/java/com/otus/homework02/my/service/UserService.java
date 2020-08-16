package com.otus.homework02.my.service;

import com.otus.homework02.my.domain.User;
import com.otus.homework02.my.exceptions.MyBadRequestException;
import com.otus.homework02.my.exceptions.MyResourceNotFoundException;

public interface UserService {

    User lookupById(Integer userId) throws MyResourceNotFoundException;
    User createUser(Integer userId, String title, String description) throws MyBadRequestException;
    void updateUser(Integer userId, Integer categoryId, User user) throws MyBadRequestException;
    void removeUser(Integer userId, Integer categoryId) throws MyResourceNotFoundException;

}
