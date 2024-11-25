package com.project.spaceRequestService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "SpaceRequest Service API",
				version = "1.0",
				description = "API para gerenciar requisições à spaces do sistema"
		)
)
@EnableDiscoveryClient
public class SpaceRequestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaceRequestServiceApplication.class, args);
	}

}