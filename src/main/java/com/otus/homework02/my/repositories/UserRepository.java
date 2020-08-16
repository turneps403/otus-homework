package com.otus.homework02.my.repositories;

import com.otus.homework02.my.domain.User;
import com.otus.homework02.my.exceptions.MyBadRequestException;
import com.otus.homework02.my.exceptions.MyResourceNotFoundException;

public interface UserRepository {

    Integer create(String fristName, String lastName, String email, String phone) throws MyBadRequestException;
    User read(Integer userId) throws MyResourceNotFoundException;
    void update(Integer userId, User user) throws MyBadRequestException;
    void delete(Integer userId);

}
