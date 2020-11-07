package com.otus.homework.my.service;

import com.otus.homework.my.dao.Bill;
import com.otus.homework.my.events.BillingEvent;
import com.otus.homework.my.repositories.BillH2Repositry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillH2Service {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BillH2Repositry repo;

    public boolean apply(BillingEvent event) {
        log.info("work with event {}", event);
        Optional<Bill> optBill = repo.findById(event.getUserID());
        Bill bill = optBill.isPresent() ? optBill.get() : null;
        log.info("bill is {}", bill);
        if (bill == null) {
            if (event.getAmount() < 0) {
                return false;
            }
            Bill newBill = new Bill();
            newBill.setUserID(event.getUserID());
            newBill.setBalance(event.getAmount());
            log.info("bill is {}", newBill);
            repo.save(newBill);
            log.info("after save");
            return true;
        }
        if (event.getAmount() == 0) {
            return false;
        }
        if (event.getAmount() < 0 && bill.getBalance() + event.getAmount() < 0) {
            return false;
        }
        bill.setBalance(bill.getBalance() + event.getAmount());
        repo.save(bill);
        log.info("after save bill is {}", bill);
        return true;
    }

    public Bill selectByUserID(String userID) {
        Optional<Bill> optBill = repo.findById(userID);
        Bill bill = optBill.isPresent() ? optBill.get() : null;
        return bill;
    }

}
