package com.otus.homework.my.resources;

import com.otus.homework.my.component.SimpleDto;
import com.otus.homework.my.configs.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class Test {
    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private KafkaTemplate<String, SimpleDto> kafkaTemplate;

//    @Autowired
//    private KafkaProducer config;
//
//    @GetMapping(value = "/b")
//    public Map<String, String> importDialRecord() {
//        return Collections.singletonMap("config", config.kafkaTemplate().toString());
//    }

    @GetMapping(value = "/aa")
    public Map<String, String> importDialRecord(String req) {
        String topic = "test1";
        String message = req;
        log.info("request test req: {}", req);
        log.info("sending message='{}' to topic='{}'", message, topic);
        SimpleDto oo = new SimpleDto();
        oo.foo = "FOO";
        oo.bar = "BAR";
        kafkaTemplate.send(topic, oo);
        log.info("All sended");
        return Collections.singletonMap("topic", message);
    }

    @RequestMapping("/test")
    public Map<String, String> lookup() {
        return Collections.singletonMap("foo", "bar");
    }
}
