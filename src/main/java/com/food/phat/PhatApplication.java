package com.food.phat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.food.phat.repository")
public class PhatApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhatApplication.class, args);
	}

}