package com.SzafrankoMussiMasucci.TPO_progra3.config;

import org.neo4j.driver.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.ReactiveDatabaseSelectionProvider;
import org.springframework.data.neo4j.core.transaction.ReactiveNeo4jTransactionManager;

@Configuration
public class Neo4JConfig {

    @Bean
    public ReactiveNeo4jTransactionManager reactiveTransactionManager(Driver driver,
                                                                      ReactiveDatabaseSelectionProvider databaseSelectionProvider) {
        return new ReactiveNeo4jTransactionManager(driver, databaseSelectionProvider);
    }
}