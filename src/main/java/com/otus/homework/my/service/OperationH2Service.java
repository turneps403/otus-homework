package com.otus.homework.my.service;

import com.otus.homework.my.dao.Operation;
import com.otus.homework.my.dao.OperationHistory;
import com.otus.homework.my.events.OperationEvent;
import com.otus.homework.my.repositories.OperationH2Repositry;
import com.otus.homework.my.repositories.OperationHistoryH2Repositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OperationH2Service {
    @Autowired
    private OperationH2Repositry repo;

    @Autowired
    private OperationHistoryH2Repositry hrepo;

    public void markAsSuccess(String operID) {
        Optional<Operation> oop = repo.findById(operID);
        if (oop.isPresent()) {
            Operation op = oop.get();
            op.setStatusDone();
            repo.save(op);
        }
    }

    public void markAsFailed(String operID) {
        Operation op = repo.findById(operID).orElseThrow(()
                -> new IllegalArgumentException("Invalid operID:" + operID));
        op.setStatusFailed();
        repo.save(op);
    }

    public Operation create(OperationEvent event) {
        Operation op = new Operation(event.getOperID(), event.getAmount());
        OperationHistory hop = new OperationHistory(event.getUserID(), event.getOperID());
        repo.save(op);
        hrepo.save(hop);
        return op;
    }

    public List<Operation> getAllByUserID(String userID) {
        Iterable<OperationHistory> hres = hrepo.findAllById( new ArrayList<String>(){{ add(userID); }} );
        List<String> opIDs = new ArrayList<>();
        hres.forEach((op) -> opIDs.add(op.getOperID()));

        Iterable<Operation> res = repo.findAllById(opIDs);
        List<Operation> ret = new ArrayList<>();
        res.forEach((op) -> ret.add(op));

        return ret;
    }
}
