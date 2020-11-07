package com.otus.homework.my.repositories;

import com.otus.homework.my.dao.OperationHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationHistoryH2Repositry extends CrudRepository<OperationHistory, String> {
    //@Query("SELECT operid, userid FROM operationhistory WHERE userid=:userID")
//    List<OperationHistory> findAllByUserID(String userID);

}
