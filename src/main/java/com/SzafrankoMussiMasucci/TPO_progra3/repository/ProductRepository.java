package com.SzafrankoMussiMasucci.TPO_progra3.repository;

import com.SzafrankoMussiMasucci.TPO_progra3.Model.ProductEntity;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveNeo4jRepository<ProductEntity, String> {
    Mono<ProductEntity> findOneByName(String name);
}