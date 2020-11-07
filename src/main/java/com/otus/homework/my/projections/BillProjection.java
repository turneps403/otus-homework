package com.otus.homework.my.projections;

import com.otus.homework.my.dao.Bill;
import com.otus.homework.my.queries.UserBillInfoQuery;
import com.otus.homework.my.service.BillH2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BillProjection {
    @Autowired
    private BillH2Service service;

    public Bill handle(UserBillInfoQuery query) {
        return service.selectByUserID(query.getUserID());
    }
}
