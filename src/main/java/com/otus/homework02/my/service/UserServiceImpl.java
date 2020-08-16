package com.otus.homework02.my.service;

import com.otus.homework02.my.domain.User;
import com.otus.homework02.my.exceptions.MyBadRequestException;
import com.otus.homework02.my.exceptions.MyResourceNotFoundException;
import com.otus.homework02.my.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User lookupById(Integer userId) throws MyResourceNotFoundException {
        return null;
    }

    @Override
    public User createUser(Integer userId, String title, String description) throws MyBadRequestException {
        return null;
    }

    @Override
    public void updateUser(Integer userId, Integer categoryId, User user) throws MyBadRequestException {

    }

    @Override
    public void removeUser(Integer userId, Integer categoryId) throws MyResourceNotFoundException {

    }
}
