package com.github.vvhiterussian.restmate.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@Import(ProdEntityManagerConfiguration.class)
public class TestEntityManagerConfiguration {
    @Bean
    public EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("TestPersistenceUnit");
    }
}
