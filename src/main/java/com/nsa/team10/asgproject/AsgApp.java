package com.nsa.team10.asgproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class AsgApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(AsgApp.class, args);
    }

    @Bean
    @Profile("development")
    public FlywayMigrationStrategy cleanMigrateStrategy()
    {
        return flyway ->
        {
            flyway.clean();
            flyway.migrate();
        };
    }
}
