package com.otus.homework.my.projector;

import com.otus.homework.my.events.Event;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaProjector {
    public void transfer(ConsumerRecord<?, Event> cr);
}
