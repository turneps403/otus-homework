package com.otus.homework.my.repositories;

import com.otus.homework.my.dao.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserH2Repositry extends CrudRepository<User, String> {}
