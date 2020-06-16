package com.workshop.camunda.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariDataSource dataSource() {
        return (HikariDataSource) DataSourceBuilder.create().build();
    }

    //To run flyway migration before the bpm engine gets created (also trying to read database)
    @Bean
    AbstractProcessEnginePlugin flywayInitializingProcessEnginePlugin(FlywayMigrationInitializer initializer) {
        return new AbstractProcessEnginePlugin() {
            @Override
            public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
            }
        };
    }

}