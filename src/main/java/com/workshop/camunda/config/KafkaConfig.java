package com.workshop.camunda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.DefaultKafkaHeaderMapper;

@Configuration
public class KafkaConfig {
    @Bean
    public DefaultKafkaHeaderMapper headerMapper() {
        DefaultKafkaHeaderMapper  headerMapper = new DefaultKafkaHeaderMapper();
        headerMapper.addTrustedPackages("*");
        return headerMapper;
    }
}
