package com.otus.homework.my.repositories;

import com.otus.homework.my.events.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

public class KafkaEventRepository implements EventRepository {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private String topic = "default";

    public String getTopic() {
        return topic;
    }

    @Autowired
    private KafkaTemplate<String, Event> kafkaTemplate;

    @Override
    public void save(Event ev) {
        log.warn("++ KafkaEventRepository: send event " + ev.toString() + " to topic " + this.getTopic());
        kafkaTemplate.send(this.getTopic(), ev);
    }
}
