package com.otus.homework.my.projector;

import com.otus.homework.my.events.CreateUserEvent;
import com.otus.homework.my.events.Event;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class UserKafkaProjector implements KafkaProjector {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    @KafkaListener(topics = "user")
    public void transfer(ConsumerRecord<?, Event> cr) {
        log.info("user MY MY msg==========: {}", cr.toString());
        Event event = cr.value();

    }
}
