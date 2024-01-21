package com.synthilearn.notificationservice.infra.persistence.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table("template")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TemplateEntity implements Persistable<String> {

    @Id
    private String type;
    private String payload;
    @Transient
    private boolean newRecord;

    @Override
    public String getId() {
        return type;
    }

    @Override
    public boolean isNew() {
        return newRecord;
    }
}

