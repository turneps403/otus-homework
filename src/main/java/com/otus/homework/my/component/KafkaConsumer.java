package com.otus.homework.my.component;

import com.otus.homework.my.aggregators.Aggregator;
import com.otus.homework.my.commands.CreateBillingCommand;
import com.otus.homework.my.dao.User;
import com.otus.homework.my.events.CreateBillingEvent;
import com.otus.homework.my.events.CreateUserEvent;
import com.otus.homework.my.events.Event;
import com.otus.homework.my.repositories.KafkaEventRepository;
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

    @KafkaListener(topics = "user", id = "user")
    public void user_listener(ConsumerRecord<?, Event> cr) {
        log.info("user MY MY msg==========: {}", cr.toString());
        log.info("user MY MY val==========: {}", cr.value());
        log.info("user MY MY uservice==========: {}", uservice);
        User user = uservice.createUserFromEvent((CreateUserEvent) cr.value());
        log.info("user MY MY user ==========: {}", user);
        CreateBillingCommand bill_cmd = new CreateBillingCommand(user.getID());
        log.info("user MY MY bill_cmd ==========: {}", bill_cmd);
        bagg.convertCommandToEvent(bill_cmd);
        log.info("user MY MY getEvent ==========: {}", bagg.getEvent());
        billEventRep.save(bagg.getEvent());
        log.info("user MY MY end ==========");


        // log.info("user MY MY msg getClassName==========: {}", foo.getClassName());
        // log.info("user MY MY msg getFirstName==========: {}", ((CreateUserEvent)foo).getFirstName());
        // 2020-10-31 20:16:18.225  INFO 1 --- [ntainer#0-0-C-1] c.o.homework.my.component.KafkaConsumer  : MY MY msg==========: ConsumerRecord(topic = test1, partition = 0, leaderEpoch = 0, offset = 1, CreateTime = 1604175378203, serialized key size = -1, serialized value size = 3, headers = RecordHeaders(headers = [], isReadOnly = false), key = null, value = foo)
    }

    @KafkaListener(topics = "bill", id="bill")
    public void bill_listener(ConsumerRecord<?, Event> cr) {
        log.info("bill MY MY msg==========: {}", cr.toString());
        CreateBillingEvent cmd = (CreateBillingEvent) cr.value();
         log.info("bill MY MY msg getClassName==========: {}", cmd.getClassName());
         log.info("bill MY MY msg getFirstName==========: {}", ((CreateBillingEvent) cmd).getUserID());
    }

}
