package com.otus.homework.my.repositories;

import com.otus.homework.my.domain.User;
import com.otus.homework.my.exceptions.MyBadRequestException;
import com.otus.homework.my.exceptions.MyResourceNotFoundException;

public interface UserRepository {

    Integer create(String fristName, String lastName, String email, String phone) throws MyBadRequestException;
    User read(Integer userId) throws MyResourceNotFoundException;
    void update(Integer userId, User user) throws MyBadRequestException;
    void delete(Integer userId);

}
