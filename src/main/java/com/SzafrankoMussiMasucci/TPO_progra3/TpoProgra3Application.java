package com.SzafrankoMussiMasucci.TPO_progra3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.neo4j.cypherdsl.core.renderer.Configuration;
import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class TpoProgra3Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TpoProgra3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try (var driver = GraphDatabase.driver("neo4j+s://e3634c1b.databases.neo4j.io",
				AuthTokens.basic("neo4j",
						"Fj6hL_hexu1yJc9KkdGOKEOeKUIy0QasgOiHsLbINsU"))) {
			driver.verifyConnectivity();
			System.out.println("Connection established.");
		}
	}
}
