package com.synthilearn.notificationservice.app.port;

import com.synthilearn.notificationservice.infra.persistence.jpa.entity.TemplateEntity;
import reactor.core.publisher.Mono;

public interface TemplateRepository {

    Mono<TemplateEntity> findByType(String type);
}
