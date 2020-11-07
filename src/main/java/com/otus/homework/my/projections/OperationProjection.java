package com.otus.homework.my.projections;

import com.otus.homework.my.dao.Operation;
import com.otus.homework.my.queries.UserOperationsQuery;
import com.otus.homework.my.service.OperationH2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OperationProjection {
    @Autowired
    private OperationH2Service service;

    public List<Operation> handle(UserOperationsQuery query) {
        return service.getAllByUserID(query.getUserID());
    }
}
