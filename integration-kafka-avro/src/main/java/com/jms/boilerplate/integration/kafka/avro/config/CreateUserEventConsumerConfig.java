package com.jms.boilerplate.integration.kafka.avro.config;

import com.jms.boilerplate.integration.kafka.avro.event.CreateUserEvent;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class CreateUserEventConsumerConfig {

    private final KafkaProperties kafkaProperties;

    public CreateUserEventConsumerConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    /*
        KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG is a configuration property used with the KafkaAvroDeserializer class.
        It controls how the deserializer interprets Avro data received from Kafka topics.
        Here's a breakdown of its purpose:
            Default Behavior:
                By default, KafkaAvroDeserializer uses a generic approach.
                It deserializes Avro data into a generic GenericRecord object.
                This GenericRecord provides access to the data's fields and values, but it doesn't leverage the benefits of having strongly typed representations.
            Specific Record Deserialization (Enabled with true):
                When you set KafkaAvroDeserializerConfig.SPECIFIC_avro_READER_CONFIG to true, the deserializer attempts to do something more powerful.
                It tries to find and use corresponding Java classes (POJOs) that were generated specifically from the Avro schemas used for the data in the Kafka topic.
                These classes are often called "SpecificRecord" classes because they map directly to the Avro schema definitions.
     */
    @Bean("createUserEventConsumerFactory")
    public ConsumerFactory<String, CreateUserEvent> consumerFactory() {
        Map<String, Object> props = kafkaProperties.buildConsumerProperties();
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean("createUserEventContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, CreateUserEvent> containerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CreateUserEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setCommonErrorHandler(commonErrorHandler());
        return factory;
    }

    @Bean("createUserEventCommonErrorHandler")
    public DefaultErrorHandler commonErrorHandler() {
        return new DefaultErrorHandler((consumerRecord, e) -> {
        }, new FixedBackOff(1000, 1));
    }
}
