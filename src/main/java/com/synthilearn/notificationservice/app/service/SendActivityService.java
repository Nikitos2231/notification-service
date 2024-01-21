package com.synthilearn.notificationservice.app.service;

import com.synthilearn.notificationservice.domain.Notification;
import com.synthilearn.notificationservice.domain.Status;
import com.synthilearn.notificationservice.infra.persistence.jpa.entity.SendActivityEntity;
import reactor.core.publisher.Mono;

public interface SendActivityService {

    Mono<SendActivityEntity> saveSendActivity(Notification notification, Status status, String errorReason, String template);
}
