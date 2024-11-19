package com.SzafrankoMussiMasucci.TPO_progra3.Model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Person")
public class PersonEntity {
    @Id
    private final String name;
    public PersonEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}