package com.otus.homework.my.repositories;

import com.otus.homework.my.dao.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillH2Repositry extends CrudRepository<Bill, Long> {
    Bill findByUserIDIs(String userID);
}
