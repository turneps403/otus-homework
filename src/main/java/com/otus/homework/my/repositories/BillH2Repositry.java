package com.otus.homework.my.repositories;

import com.otus.homework.my.dao.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillH2Repositry extends CrudRepository<Bill, String> {
//    @Query("SELECT operid, userid FROM bill WHERE userid=:userID")
//    List<OperationHistory> findAllByUserID(String userID);
//    Bill findByUserIDIs(String userID);
}
