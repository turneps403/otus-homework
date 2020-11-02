package com.otus.homework.my.resources;

import com.otus.homework.my.commands.CreateUserCommand;
import com.otus.homework.my.events.CreateUserEvent;
import com.otus.homework.my.events.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/cmd", produces = MediaType.APPLICATION_JSON_VALUE)
public class Command {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaTemplate<String, Event> kafkaTemplate;

    @PostMapping("/user")
    public Map<String, String> createUser(@RequestBody CreateUserCommand cmd) {
        final String userID = UUID.randomUUID().toString();

        CreateUserEvent ev = new CreateUserEvent();
        ev.setUserID(userID);
        ev.setFirstName("FirstN");
        ev.setLastName("LastN");

        kafkaTemplate.send("test1", ev);
        log.info("All sended");

        return Collections.singletonMap("userID", userID);
    }

    @GetMapping(value = "/aa")
    public Map<String, String> importDialRecord(String req) {
        String topic = "test1";
        String message = req;
        log.info("request test req: {}", req);
        log.info("sending message='{}' to topic='{}'", message, topic);

        log.info("All sended");
        return Collections.singletonMap("topic", message);
    }

    @RequestMapping("/test")
    public Map<String, String> lookup() {
        return Collections.singletonMap("foo", "bar");
    }
}
