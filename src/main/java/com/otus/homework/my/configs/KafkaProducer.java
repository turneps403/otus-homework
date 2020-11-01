package com.otus.homework.my.configs;

import com.otus.homework.my.component.SimpleDto;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
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

    @Bean
    //ConcurrentKafkaListenerContainerFactory<String, String>
    ConcurrentKafkaListenerContainerFactory<String, SimpleDto>
    kafkaListenerContainerFactory() {
        //ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        ConcurrentKafkaListenerContainerFactory<String, SimpleDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    //public ConsumerFactory<String, String>
    public ConsumerFactory<String, SimpleDto>
    consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(SimpleDto.class));
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "mykafka.zookaf.svc.cluster.local:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "foo");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }


//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, TradeModel> topicListenerContainerFactory() {
//        return tradeRequestListenerContainerFactory(topic);
//    }
//
//    public ConsumerFactory<String, TradeModel> tradeRequestConsumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "TradeRequest");
//        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(TradeModel.class));
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, TradeModel> tradeRequestListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, TradeModel> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(tradeRequestConsumerFactory());
//        return factory;
//    }


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
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "mykafka-0.mykafka-headless.zookaf.svc.cluster.local:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }



    @Bean
    public ProducerFactory<String, SimpleDto> producerFactoryO() {
        return new DefaultKafkaProducerFactory<>(producerConfigsO());
    }

    @Bean
    public Map<String, Object> producerConfigsO() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "mykafka-0.mykafka-headless.zookaf.svc.cluster.local:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public KafkaTemplate<String, SimpleDto> kafkaTemplateO() {
        return new KafkaTemplate<>(producerFactoryO());
    }


}


//@Configuration
//public class KafkaProducer {
//    @Value("${kafka.producer.servers}")
//    private String server;
//
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
//        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//
//    public ConsumerFactory<String, String> consumerFactory(String groupId) {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        return new DefaultKafkaConsumerFactory<>(props);
//    }
//
//    public ConcurrentKafkaListenerContainerFactory<String, String> tradeRequestListenerContainerFactory(String groupId) {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory(groupId));
//        return factory;
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> topicListenerContainerFactory() {
//        return tradeRequestListenerContainerFactory("test1");
//    }
//
////    @Bean
////    public ProducerFactory<String, TradeModel> tradeRequestProducerFactory() {
////        Map<String, Object> configProps = new HashMap<>();
////        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
////        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
////        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
////        return new DefaultKafkaProducerFactory<>(configProps);
////    }
////
////    @Bean
////    public KafkaTemplate<String, TradeModel> tradeRequestKafkaTemplate() {
////        return new KafkaTemplate<>(tradeRequestProducerFactory());
////    }
//}
//
////@Configuration
////@EnableKafka
////public class KafkaProducer {
////    @Value("${kafka.producer.servers}")
////    private String servers;
////    @Value("${kafka.producer.retries}")
////    private int retries;
////    @Value("${kafka.producer.batch.size}")
////    private int batchSize;
////    @Value("${kafka.producer.linger}")
////    private int linger;
////    @Value("${kafka.producer.buffer.memory}")
////    private int bufferMemory;
////
////    public Map<String, Object> producerConfigs() {
////        Map<String, Object> props = new HashMap<>();
////        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
////        props.put(ProducerConfig.RETRIES_CONFIG, retries);
////        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
////        props.put(ProducerConfig.LINGER_MS_CONFIG, linger);
////        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
////        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
////        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
////        return props;
////    }
////
////    public ProducerFactory<String, String> producerFactory() {
////        return new DefaultKafkaProducerFactory<>(producerConfigs());
////    }
////
////    @Bean
////    @Primary
////    public KafkaTemplate<String, String> kafkaTemplate() {
////        return new KafkaTemplate<String, String>(producerFactory());
////    }
////
//////    @Bean
//////    ConcurrentKafkaListenerContainerFactory<Integer, String>
//////    kafkaListenerContainerFactory() {
//////        ConcurrentKafkaListenerContainerFactory<Integer, String> factory =
//////                new ConcurrentKafkaListenerContainerFactory<>();
//////        factory.setConsumerFactory(consumerFactory());
//////        return factory;
//////    }
//////
//////    @Bean
//////    public ConsumerFactory<Integer, String> consumerFactory() {
//////        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
//////    }
//////
//////    @Bean
//////    public Map<String, Object> consumerConfigs() {
//////        Map<String, Object> props = new HashMap<>();
//////        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, embeddedKafka.getBrokersAsString());
//////        ...
//////        return props;
//////    }
////
////}
