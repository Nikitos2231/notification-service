package com.synthilearn.notificationservice.app.service;

import com.synthilearn.notificationservice.domain.Notification;
import com.synthilearn.notificationservice.infra.persistence.jpa.entity.SendActivityEntity;
import reactor.core.publisher.Mono;

public interface EmailService {

    Mono<SendActivityEntity> send(Notification notification, String topic);
}
