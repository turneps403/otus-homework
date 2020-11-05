package com.otus.homework.my.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("user")
public class UserKafkaEventRepository extends KafkaEventRepository {
    private String topic = "user";

    @Override
    public String getTopic() {
        return topic;
    }
}
