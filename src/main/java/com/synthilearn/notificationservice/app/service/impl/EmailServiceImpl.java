package com.synthilearn.notificationservice.app.service.impl;

import com.synthilearn.notificationservice.app.port.TemplateRepository;
import com.synthilearn.notificationservice.app.service.EmailService;
import com.synthilearn.notificationservice.app.service.SendActivityService;
import com.synthilearn.notificationservice.domain.Notification;
import com.synthilearn.notificationservice.domain.Status;
import com.synthilearn.notificationservice.infra.exception.TemplateException;
import com.synthilearn.notificationservice.infra.persistence.jpa.entity.SendActivityEntity;
import com.synthilearn.notificationservice.infra.persistence.jpa.entity.TemplateEntity;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateRepository templateRepository;
    private final SendActivityService sendActivityService;
    private final String TEMPLATE_NOT_FOUND_MESSAGE_TEMPLATE = "Template with type: %s not found";

    public Mono<SendActivityEntity> send(Notification notification, String topic) {
        return templateRepository.findByType(topic)
                .singleOptional()
                .flatMap(templateOpt -> {
                    if (templateOpt.isEmpty()) {
                        String message = String.format(TEMPLATE_NOT_FOUND_MESSAGE_TEMPLATE, topic);
                        log.error(message);
                        return Mono.error(TemplateException.notFound(message));
                    }

                    return processMessage(notification, templateOpt.get());
                });
    }

    @SneakyThrows
    private Mono<SendActivityEntity> processMessage(Notification notification, TemplateEntity template) {
        String errorReason = null;
        Status status = Status.SUCCESS;
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setSubject(notification.getEmail());
            helper.setText(processTemplate(notification, template), true);
            helper.setTo(notification.getEmail());
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            errorReason = e.getMessage();
            status = Status.ERROR;
            log.error("Error has occurred while sending notification. Notification: {}, error: {}", notification, e);
        }
        return sendActivityService.saveSendActivity(notification, status, errorReason, template.getType());
    }

    private String processTemplate(Notification notification, TemplateEntity template) {
        notification.getPayload()
                .forEach((key, value) -> template.setPayload(template.getPayload().replaceAll("%" + key + "%", (String) value)));
        return template.getPayload();
    }
}
