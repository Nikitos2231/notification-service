package com.synthilearn.notificationservice;

import com.synthilearn.loggingstarter.EnableLogging;
import com.synthilearn.notificationservice.app.configuration.CustomKafkaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableLogging
@EnableConfigurationProperties(CustomKafkaProperties.class)
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

}
