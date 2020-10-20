package com.otus.homework.my.service;

import com.otus.homework.my.domain.User;
import com.otus.homework.my.exceptions.MyBadRequestException;
import com.otus.homework.my.exceptions.MyResourceNotFoundException;
import com.otus.homework.my.repositories.UserRepository;
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
        return userRepository.read(userId);
    }

    @Override
    public User lookupByEmail(String email) throws MyResourceNotFoundException {
        return userRepository.read(email);
    }

    @Override
    public User createUser(String fristName, String lastName, String email, String phone, String password)
            throws MyBadRequestException, MyResourceNotFoundException
    {
        Integer user_id = userRepository.create(fristName, lastName, email, phone, password);
        return this.lookupById(user_id);
    }

    public User createUser(User user) throws MyBadRequestException, MyResourceNotFoundException
    {
        return this.createUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(), user.getPassword());
    }

    @Override
    public void updateUser(Integer userId, User user) throws MyBadRequestException {
        userRepository.update(userId, user);
    }

    @Override
    public void removeUser(Integer userId) throws MyResourceNotFoundException {
        userRepository.delete(userId);
    }

}
