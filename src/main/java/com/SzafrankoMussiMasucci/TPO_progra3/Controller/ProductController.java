package com.SzafrankoMussiMasucci.TPO_progra3.Controller;

import com.SzafrankoMussiMasucci.TPO_progra3.Model.ProductEntity;
import com.SzafrankoMussiMasucci.TPO_progra3.repository.ProductRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.SzafrankoMussiMasucci.TPO_progra3.Model.Grafo;

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

    @GetMapping("/grafo")
    public Mono<String> getGrafo() {
        return prodRepository.findAll()
                .collectList() // Convertimos el Flux a un Mono<List<ProductEntity>>
                .map(products -> {
                    Grafo grafo = new Grafo(products); // Construimos el grafo con la lista de productos
                    return grafo.toString(); // Devolvemos la representación en String del grafo
                });
    }
    @GetMapping("/bfs")
    public Mono<String> getBFS() {
        return prodRepository.findAll()
                .collectList() // Convertimos el Flux a un Mono<List<ProductEntity>>
                .map(products -> {
                    Grafo grafo = new Grafo(products); // Construimos el grafo con la lista de products
                    return grafo.BFS(products.get(0)); // Devolvemos la representación en String del grafo
                });
    }

    @GetMapping("/dfs")
    public Mono<String> getDFS() {
        return prodRepository.findAll()
                .collectList() // Convertimos el Flux a un Mono<List<ProductEntity>>
                .map(products -> {
                    Grafo grafo = new Grafo(products); // Construimos el grafo con la lista de productos
                    return grafo.DFS(products.get(0)); // Devolvemos la representación en String del grafo
                });
    }

}
