package com.synthilearn.notificationservice.app.configuration;

import com.synthilearn.notificationservice.domain.Notification;
import com.synthilearn.notificationservice.infra.persistence.jpa.entity.TemplateEntity;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ReactiveKafkaProducerConfig {

    private final CustomKafkaProperties customKafkaProperties;

    @Bean
    public ReceiverOptions<String, Notification> kafkaReceiverOptions() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, customKafkaProperties.getBootstrapServer());
        config.put(ConsumerConfig.GROUP_ID_CONFIG, customKafkaProperties.getGroupId());
        config.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.synthilearn.notificationservice.domain.Notification");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        ReceiverOptions<String, Notification> basicReceiverOptions = ReceiverOptions.create(config);
        return basicReceiverOptions.subscription(customKafkaProperties.getTopics());
    }

    @Bean
    public ReactiveKafkaConsumerTemplate<String, Notification> reactiveKafkaConsumerTemplate(ReceiverOptions<String, Notification> kafkaReceiverOptions) {
        return new ReactiveKafkaConsumerTemplate<>(kafkaReceiverOptions);
    }
}
