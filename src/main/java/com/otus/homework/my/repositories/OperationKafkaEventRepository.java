package com.otus.homework.my.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("operation")
public class OperationKafkaEventRepository extends KafkaEventRepository {
    private String topic = "operation";

    @Override
    public String getTopic() {
        return topic;
    }
}
