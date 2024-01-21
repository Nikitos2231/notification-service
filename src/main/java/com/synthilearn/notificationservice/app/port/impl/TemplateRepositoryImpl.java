package com.synthilearn.notificationservice.app.port.impl;

import com.synthilearn.notificationservice.app.port.TemplateRepository;
import com.synthilearn.notificationservice.infra.persistence.jpa.entity.TemplateEntity;
import com.synthilearn.notificationservice.infra.persistence.jpa.repository.TemplateJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TemplateRepositoryImpl implements TemplateRepository {

    private final TemplateJpaRepository templateJpaRepository;

    @Override
    public Mono<TemplateEntity> findByType(String type) {
        return templateJpaRepository.findById(type);
    }
}
