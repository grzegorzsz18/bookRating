package com.scheduler.bookservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Data
public class ConfigReader {

    private String applicationKey;
    private String applicationCx;
    private Integer connectionTimeout;
}