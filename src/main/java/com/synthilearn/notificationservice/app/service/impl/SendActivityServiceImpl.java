package com.synthilearn.notificationservice.app.service.impl;

import com.synthilearn.notificationservice.app.port.SendActivityRepository;
import com.synthilearn.notificationservice.app.service.SendActivityService;
import com.synthilearn.notificationservice.domain.Notification;
import com.synthilearn.notificationservice.domain.SendActivity;
import com.synthilearn.notificationservice.domain.Status;
import com.synthilearn.notificationservice.infra.persistence.jpa.entity.SendActivityEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendActivityServiceImpl implements SendActivityService {

    private final SendActivityRepository sendActivityRepository;


    @Override
    public Mono<SendActivityEntity> saveSendActivity(Notification notification, Status status, String errorReason, String template) {
        return sendActivityRepository.save(initSaveActivity(notification, status, errorReason, template))
                .doOnError((error) -> log.error("Error has occurred while saving sendActivity by notification: {}, error: {}", notification, error));
    }

    private SendActivity initSaveActivity(Notification notification, Status status, String errorReason, String template) {
        return SendActivity.builder()
                .email(notification.getEmail())
                .templateType(template)
                .status(status)
                .errorReason(errorReason)
                .build();
    }
}
