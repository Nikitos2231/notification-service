package com.synthilearn.notificationservice.infra.persistence.jpa.repository;

import com.synthilearn.notificationservice.infra.persistence.jpa.entity.TemplateEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateJpaRepository extends ReactiveCrudRepository<TemplateEntity, String> {
}
