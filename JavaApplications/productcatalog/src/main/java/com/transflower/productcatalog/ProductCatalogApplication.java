package com.transflower.productcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductCatalogApplication {

	public static void main(String[] args) {
		System.out.println( "Hello World!" );
		SpringApplication.run(ProductCatalogApplication.class, args);
	}

}
