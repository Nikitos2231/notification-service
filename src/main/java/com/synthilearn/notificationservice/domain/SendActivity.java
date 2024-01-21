package com.synthilearn.notificationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class SendActivity {
    private String templateType;
    private String email;
    private Status status;
    private String errorReason;
}
