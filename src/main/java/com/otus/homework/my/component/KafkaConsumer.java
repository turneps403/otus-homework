package com.otus.homework.my.component;

import com.otus.homework.my.aggregators.Aggregator;
import com.otus.homework.my.commands.CreateBillingCommand;
import com.otus.homework.my.dao.Operation;
import com.otus.homework.my.dao.User;
import com.otus.homework.my.events.BillingEvent;
import com.otus.homework.my.events.CreateUserEvent;
import com.otus.homework.my.events.Event;
import com.otus.homework.my.events.OperationEvent;
import com.otus.homework.my.repositories.KafkaEventRepository;
import com.otus.homework.my.repositories.OperationH2Repositry;
import com.otus.homework.my.service.BillH2Service;
import com.otus.homework.my.service.OperationH2Service;
import com.otus.homework.my.service.UserH2Service;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("bill")
    private Aggregator bagg;

    @Autowired
    @Qualifier("bill")
    private KafkaEventRepository billEventRep;

    @Autowired
    private UserH2Service uservice;

    @Autowired
    private BillH2Service bservice;

    @Autowired
    private OperationH2Service oservice;

    @KafkaListener(topics = "user", id = "user")
    public void user_listener(ConsumerRecord<?, Event> cr) {
         log.info("user ==========: {}", cr.toString());
        // log.info("user MY MY val==========: {}", cr.value());
        User user = uservice.createUserFromEvent((CreateUserEvent) cr.value());
        CreateBillingCommand bill_cmd = new CreateBillingCommand(user.getID());
        bagg.convertCommandToEvent(bill_cmd);
        billEventRep.save(bagg.getEvent());
    }

    @KafkaListener(topics = "bill", id="bill")
    public void bill_listener(ConsumerRecord<?, Event> cr) {
        log.info("bill ==========: {}", cr.toString());
        BillingEvent event = (BillingEvent) cr.value();
        Boolean res = bservice.apply(event);
        log.info("after apply ========== {}", res);
        if (event.getOperID() != "") {
            if (event.getAmount() > 0) {
                log.info("Balance was top uped");
                // expect already success
            } else if (event.getAmount() < 0) {
                if (res) {
                    log.info("Withdraw was successful");
                    oservice.markAsSuccess(event.getOperID());
                } else {
                    log.warn("Withdraw was failed");
                    oservice.markAsFailed(event.getOperID());
                }
            }
        } else {
            if (!res) {
                log.warn("Account already exists");
            } else {
                log.info("Account was successfuly created");
            }
        }

    }

    @KafkaListener(topics = "operation", id="operation")
    public void operation_listener(ConsumerRecord<?, Event> cr) {
        log.info("oper ==========: {}", cr.toString());
        OperationEvent event = (OperationEvent) cr.value();
        Operation op = oservice.create(event);
        log.info("oper op ==========: {}", op.toString());
        BillingEvent bevent = new BillingEvent(
                event.getUserID(),
                event.getAmount() > 0 ? -1 * event.getAmount() : event.getAmount(),
                event.getOperID()
        );
        billEventRep.save(bevent);
    }

}
