package com.synthilearn.notificationservice.app.configuration;

import com.synthilearn.notificationservice.app.service.impl.EmailServiceImpl;
import com.synthilearn.notificationservice.domain.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Service
public class KafkaListener implements CommandLineRunner  {

    private final ReactiveKafkaConsumerTemplate<String, Notification> reactiveKafkaConsumerTemplate;
    private final EmailServiceImpl emailService;

    public Flux<Notification> startKafkaConsumer() {
        return reactiveKafkaConsumerTemplate
                .receiveAutoAck()
                .flatMap(consumerRecord -> emailService.send(consumerRecord.value(), consumerRecord.topic())
                        .then(Mono.just(consumerRecord.value())))
                .doOnError(throwable -> log.error("something bad happened while consuming : {}", throwable.getMessage()));
    }

    @Override
    public void run(String... args) {
        startKafkaConsumer().subscribe();
    }
}
