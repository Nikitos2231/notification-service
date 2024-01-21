package com.synthilearn.notificationservice.app.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;

@Data
@RefreshScope
@ConfigurationProperties(prefix = "kafka")
public class CustomKafkaProperties {

    private String bootstrapServer;
    private String groupId;
    private List<String> topics;
}
