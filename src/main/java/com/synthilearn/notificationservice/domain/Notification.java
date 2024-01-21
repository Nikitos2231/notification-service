package com.synthilearn.notificationservice.domain;

import lombok.Data;

import java.util.Map;

@Data
public class Notification {

    private Map<String, Object> payload;

    public String getEmail() {
        return payload.get("email").toString();
    }
}
