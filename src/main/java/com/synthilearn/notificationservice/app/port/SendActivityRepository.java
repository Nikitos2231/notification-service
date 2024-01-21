package com.synthilearn.notificationservice.app.port;

import com.synthilearn.notificationservice.domain.SendActivity;
import com.synthilearn.notificationservice.infra.persistence.jpa.entity.SendActivityEntity;
import reactor.core.publisher.Mono;

public interface SendActivityRepository {

    Mono<SendActivityEntity> save(SendActivity sendActivity);
}
