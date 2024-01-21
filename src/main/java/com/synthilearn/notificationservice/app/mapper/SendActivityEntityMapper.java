package com.synthilearn.notificationservice.app.mapper;

import com.synthilearn.notificationservice.domain.SendActivity;
import com.synthilearn.notificationservice.infra.persistence.jpa.entity.SendActivityEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SendActivityEntityMapper {

    SendActivityEntity map(SendActivity sendActivity);
}
