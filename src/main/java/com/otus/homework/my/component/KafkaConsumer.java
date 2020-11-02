package com.otus.homework.my.component;

import com.otus.homework.my.events.CreateUserEvent;
import com.otus.homework.my.events.Event;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private Logger log = LoggerFactory.getLogger(this.getClass());

//    @KafkaListener(id = "foo", topics = "test1")
//    public void onMessage(@Payload String message, @Headers MessageHeaders headers) {
//        headers.keySet().forEach(key -> log.info("headers ========== ks: {}: {}", key, headers.get(key)));
//        log.warn("MY MY msg==========: {}", message);
////        2020-11-01 08:29:27.917  INFO 1 --- [      foo-0-C-1] c.o.homework.my.component.KafkaConsumer  : headers ========== ks: kafka_offset: 2
////        2020-11-01 08:29:27.918  INFO 1 --- [      foo-0-C-1] c.o.homework.my.component.KafkaConsumer  : headers ========== ks: kafka_consumer: org.apache.kafka.clients.consumer.KafkaConsumer@744f18be
////        2020-11-01 08:29:27.918  INFO 1 --- [      foo-0-C-1] c.o.homework.my.component.KafkaConsumer  : headers ========== ks: kafka_timestampType: CREATE_TIME
////        2020-11-01 08:29:27.919  INFO 1 --- [      foo-0-C-1] c.o.homework.my.component.KafkaConsumer  : headers ========== ks: kafka_receivedPartitionId: 0
////        2020-11-01 08:29:27.931  INFO 1 --- [      foo-0-C-1] c.o.homework.my.component.KafkaConsumer  : headers ========== ks: kafka_receivedTopic: test1
////        2020-11-01 08:29:27.931  INFO 1 --- [      foo-0-C-1] c.o.homework.my.component.KafkaConsumer  : headers ========== ks: kafka_receivedTimestamp: 1604219367800
////        2020-11-01 08:29:27.932  INFO 1 --- [      foo-0-C-1] c.o.homework.my.component.KafkaConsumer  : headers ========== ks: kafka_groupId: foo
////        2020-11-01 08:29:27.948  WARN 1 --- [      foo-0-C-1] c.o.homework.my.component.KafkaConsumer  : MY MY msg==========: foo
//    }

    @KafkaListener(topics = "test1")
    public void listen(ConsumerRecord<?, Event> cr) {
        log.info("MY MY msg==========: {}", cr.toString());
        Event foo = cr.value();
        log.info("MY MY msg getClassName==========: {}", foo.getClassName());
        log.info("MY MY msg getFirstName==========: {}", ((CreateUserEvent)foo).getFirstName());
        // 2020-10-31 20:16:18.225  INFO 1 --- [ntainer#0-0-C-1] c.o.homework.my.component.KafkaConsumer  : MY MY msg==========: ConsumerRecord(topic = test1, partition = 0, leaderEpoch = 0, offset = 1, CreateTime = 1604175378203, serialized key size = -1, serialized value size = 3, headers = RecordHeaders(headers = [], isReadOnly = false), key = null, value = foo)
    }

//    @KafkaListener(id = "foo", topics = "test1")
//    public void listen1(String foo) {
//        this.latch1.countDown();
//    }
}
