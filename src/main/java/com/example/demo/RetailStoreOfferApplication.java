package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class RetailStoreOfferApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailStoreOfferApplication.class, args);
	}

}
