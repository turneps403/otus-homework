package com.otus.homework.my.resources;

import com.otus.homework.my.aggregators.Aggregator;
import com.otus.homework.my.aggregators.UserAggregator;
import com.otus.homework.my.commands.CreateUserCommand;
import com.otus.homework.my.commands.OperationCommand;
import com.otus.homework.my.commands.TopUpBillingCommand;
import com.otus.homework.my.events.CreateUserEvent;
import com.otus.homework.my.events.Event;
import com.otus.homework.my.repositories.KafkaEventRepository;
import com.otus.homework.my.repositories.UserKafkaEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(path = "/cmd", produces = MediaType.APPLICATION_JSON_VALUE)
public class Command {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("user")
    private Aggregator uagg;

    @Autowired
    @Qualifier("user")
    private KafkaEventRepository userEventRep;

    @Autowired
    @Qualifier("bill")
    private Aggregator bagg;

    @Autowired
    @Qualifier("bill")
    private KafkaEventRepository billEventRep;

    @Autowired
    @Qualifier("operation")
    private Aggregator oagg;

    @Autowired
    @Qualifier("operation")
    private  KafkaEventRepository operEventBill;


    @PostMapping("/user")
    public Map<String, String> createUser(@RequestBody CreateUserCommand cmd) {
        uagg.convertCommandToEvent(cmd);
        userEventRep.save(uagg.getEvent());
        return Collections.singletonMap("userID", ((CreateUserEvent)uagg.getEvent()).getUserID());
    }

    @PostMapping("/topup")
    public Map<String, String> topUpBill(@RequestBody TopUpBillingCommand cmd) {
        bagg.convertCommandToEvent(cmd);
        billEventRep.save(bagg.getEvent());
        return Collections.singletonMap("ActionID", bagg.getEvent().getEventID().toString());
    }

    @PostMapping("/operation")
    public Map<String, String> operation(@RequestBody OperationCommand cmd) {
        oagg.convertCommandToEvent(cmd);
        operEventBill.save(oagg.getEvent());
        return Collections.singletonMap("ActionID", oagg.getEvent().getEventID().toString());
    }

}
