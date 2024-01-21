package com.synthilearn.notificationservice.infra.persistence.jpa.entity;

import com.synthilearn.notificationservice.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.ZonedDateTime;
import java.util.UUID;

@Table("send_activity")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SendActivityEntity implements Persistable<UUID> {

    @Id
    private UUID id;
    @Column("template_type")
    private String templateType;
    private String email;
    private Status status;
    @Column("error_reason")
    private String errorReason;
    @Column("send_time")
    private ZonedDateTime sendTime;

    @Transient
    private boolean newRecord;

    @Override
    public boolean isNew() {
        return newRecord;
    }
}
