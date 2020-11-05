package com.otus.homework.my.configs;

import com.otus.homework.my.events.Event;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableKafka
public class KafkaProducer {
    @Value("${mykafka.consumer.url}")
    private String kConsumerValue;

    @Value("${mykafka.producer.url}")
    private String kProducerValue;

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Event>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Event> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Event>
    consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(Event.class)
        );
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kConsumerValue);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "foo");
        return props;
    }

    @Bean
    public KafkaProperties.Listener listener() {
        return new KafkaProperties.Listener();
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kProducerValue);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, Event> producerFactoryO() {
        return new DefaultKafkaProducerFactory<>(producerConfigsO());
    }

    @Bean
    public Map<String, Object> producerConfigsO() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kProducerValue);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public KafkaTemplate<String, Event> kafkaTemplateO() {
        return new KafkaTemplate<>(producerFactoryO());
    }

}