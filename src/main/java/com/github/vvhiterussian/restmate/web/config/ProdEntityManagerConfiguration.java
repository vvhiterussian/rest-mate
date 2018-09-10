package com.github.vvhiterussian.restmate.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = {"com.github.vvhiterussian.restmate"})
public class ProdEntityManagerConfiguration {

    @Bean
    public EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("DevPersistenceUnit");
    }

    @Bean
    public EntityManager getEntityManager(@Autowired EntityManagerFactory emf) {
        return emf.createEntityManager();
    }

}
