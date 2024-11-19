package com.SzafrankoMussiMasucci.TPO_progra3.Controller;

import com.SzafrankoMussiMasucci.TPO_progra3.Model.ProductEntity;
import com.SzafrankoMussiMasucci.TPO_progra3.repository.ProductRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository prodRepository;
    public ProductController(ProductRepository prodRepository) {
        this.prodRepository = prodRepository;
    }

    @PutMapping
    Mono<ProductEntity> createOrUpdateProduct(@RequestBody ProductEntity newProduct) {
        return prodRepository.save(newProduct);
    }

    @GetMapping(value = { "", "/" }, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<ProductEntity> getProducts() {
        return prodRepository.findAll();
    }



}
