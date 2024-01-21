package com.synthilearn.notificationservice.infra.persistence.jpa.repository;

import com.synthilearn.notificationservice.infra.persistence.jpa.entity.SendActivityEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SendActivityJpaRepository extends ReactiveCrudRepository<SendActivityEntity, UUID> {
}
