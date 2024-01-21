package com.synthilearn.notificationservice.app.port.impl;

import com.synthilearn.notificationservice.app.mapper.SendActivityEntityMapper;
import com.synthilearn.notificationservice.app.port.SendActivityRepository;
import com.synthilearn.notificationservice.domain.SendActivity;
import com.synthilearn.notificationservice.infra.persistence.jpa.entity.SendActivityEntity;
import com.synthilearn.notificationservice.infra.persistence.jpa.repository.SendActivityJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SendActivityRepositoryImpl implements SendActivityRepository {

    private final SendActivityJpaRepository sendActivityJpaRepository;
    private final SendActivityEntityMapper sendActivityEntityMapper;

    @Override
    @Transactional
    public Mono<SendActivityEntity> save(SendActivity sendActivity) {
        return sendActivityJpaRepository.save(sendActivityEntityMapper.map(sendActivity).toBuilder()
                .sendTime(ZonedDateTime.now())
                .newRecord(true)
                .id(UUID.randomUUID())
                .build());
    }
}
