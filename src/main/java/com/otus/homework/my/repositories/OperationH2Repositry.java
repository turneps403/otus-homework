package com.otus.homework.my.repositories;

import com.otus.homework.my.dao.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationH2Repositry extends CrudRepository<Operation, String> {}
