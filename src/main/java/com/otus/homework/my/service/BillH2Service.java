package com.otus.homework.my.service;

import com.otus.homework.my.dao.Bill;
import com.otus.homework.my.events.CreateBillingEvent;
import com.otus.homework.my.repositories.BillH2Repositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillH2Service {
    @Autowired
    private BillH2Repositry repo;

    public void handle(CreateBillingEvent event) {
        Bill bill = repo.findByUserIDIs(event.getUserID());
        if (bill == null) {
            bill = new Bill();
            bill.setUserID(event.getUserID());
            bill.setBalance(0);
            repo.save(bill);
        }
        return;
    }
}
