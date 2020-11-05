package com.otus.homework.my.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("bill")
public class BillKafkaEventRepository extends KafkaEventRepository {
    private String topic = "bill";

    @Override
    public String getTopic() {
        return topic;
    }
}
