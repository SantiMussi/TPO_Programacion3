package com.SzafrankoMussiMasucci.TPO_progra3.Model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;

@Node("Product")
public class ProductEntity {
    @Id
    private final String name;
    @Property("type")
    private final String type;
    @Relationship(type = "BOUGHT", direction = INCOMING)
    private Set<PersonEntity> personBought = new HashSet<>();
    @Relationship(type = "WANTS", direction = INCOMING)
    private Set<PersonEntity> personWant = new HashSet<>();

    public ProductEntity(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setPersonWant(Set<PersonEntity> personWant) {
        this.personWant = personWant;
    }

    public void setPersonBought(Set<PersonEntity> personBought) {
        this.personBought = personBought;
    }
}