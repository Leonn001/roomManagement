package com.project.spaceService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Space Service API",
				version = "1.0",
				description = "API para gerenciar spaces no sistema"
		)
)
@EnableDiscoveryClient
public class SpaceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaceServiceApplication.class, args);
	}

}