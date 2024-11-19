package com.SzafrankoMussiMasucci.TPO_progra3.Model;

import java.util.*;

public class Grafo {
    private Map<ProductEntity, List<PersonEntity>> adjProducts;
    private Map<PersonEntity, List<ProductEntity>> adjPeople;
    public Grafo() {}
    public Grafo(List<ProductEntity> products) {
        adjProducts = new HashMap<>();
        adjPeople = new HashMap<>();
        for (ProductEntity product : products) {
            adjProducts.putIfAbsent(product, new ArrayList<>(product.getWanters()));
            adjProducts.get(product).addAll(product.getBoughters());
            for (PersonEntity wanter : product.getWanters()) {
                adjPeople.putIfAbsent(wanter, new ArrayList<>());
                adjPeople.get(wanter).add(product);
            }
            for (PersonEntity boughter : product.getBoughters()) {
                adjPeople.putIfAbsent(boughter, new ArrayList<>());
                adjPeople.get(boughter).add(product);
            }
        }
    }
    public void agregarArista(ProductEntity product, PersonEntity person) {
        adjProducts.get(product).add(person);
        adjPeople.get(person).add(product);
    }
    public String BFS(ProductEntity inicio) {
        StringBuilder resultado = new StringBuilder();
        Set<ProductEntity> visitadoProducts = new HashSet<>();
        Set<PersonEntity> visitadoPeople = new HashSet<>();
        Queue<Object> cola = new LinkedList<>();
        visitadoProducts.add(inicio);
        cola.add(inicio);
        while (!cola.isEmpty()) {
            Object actual = cola.poll();
            if (actual instanceof ProductEntity) {
                ProductEntity product = (ProductEntity) actual;
                resultado.append("Movie: ").append(product.getName()).append("\n");
                for (PersonEntity persona : adjProducts.get(product)) {
                    if (!visitadoPeople.contains(persona)) {
                        visitadoPeople.add(persona);
                        cola.add(persona);
                    }
                }
            } else if (actual instanceof PersonEntity) {
                PersonEntity persona = (PersonEntity) actual;
                System.out.println("Person: " + persona.getName());
                for (ProductEntity movie : adjPeople.get(persona)) {
                    if (!visitadoProducts.contains(movie)) {
                        visitadoProducts.add(movie);
                        cola.add(movie);
                    }
                }
            }
        }
        return resultado.toString();
    }
    public String DFS(ProductEntity inicio) {
        StringBuilder resultado = new StringBuilder();
        Set<ProductEntity> visitadoProducts = new HashSet<>();
        Set<PersonEntity> visitadoPeople = new HashSet<>();
        dfsRecursivo(inicio, resultado, visitadoProducts, visitadoPeople);
        return resultado.toString();
    }
    // Método recursivo para el recorrido DFS
    private void dfsRecursivo(Object nodo, StringBuilder resultado,
                              Set<ProductEntity> visitadoProducts, Set<PersonEntity> visitadoPeople) {
        if (nodo instanceof ProductEntity) {
            ProductEntity product = (ProductEntity) nodo;
            if (visitadoProducts.contains(product)) return; // Evitar ciclos
            visitadoProducts.add(product);
            resultado.append("Product: ").append(product.getName()).append("\n");
// Llamada recursiva para cada persona (Quien desea o comprador) conectada a la película
            for (PersonEntity persona : adjProducts.get(product)) {
                dfsRecursivo(persona, resultado, visitadoProducts, visitadoPeople);
            }
        } else if (nodo instanceof PersonEntity) {
            PersonEntity persona = (PersonEntity) nodo;
            if (visitadoPeople.contains(persona)) return; // Evitar ciclos
            visitadoPeople.add(persona);
            resultado.append("Person: ").append(persona.getName()).append("\n");
// Llamada recursiva para cada película conectada a esta persona
            for (ProductEntity movie : adjPeople.get(persona)) {
                dfsRecursivo(movie, resultado, visitadoProducts, visitadoPeople);
            }
        }
    }
    @Override
    public String toString() {
        return "Grafo{" +
                "adjProducts=" + adjProducts +
                ", adjPeople=" + adjPeople +
                '}';
    }
}