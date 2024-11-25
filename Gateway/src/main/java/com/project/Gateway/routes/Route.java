package com.project.Gateway.routes;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import java.net.URI;
import java.util.List;

@Configuration
public class Route {

    private static final String USER_SERVICE_BASE_URL = "http://localhost:8081";
    private static final String SPACE_SERVICE_BASE_URL = "http://localhost:8082";
    private static final String SPACE_REQUEST_SERVICE_BASE_URL = "http://localhost:8083";

    private static final String FALLBACK_ROUTE = "forward:/fallbackRoute";


    // ===== Space request Routes =====
    @Bean
    public RouterFunction<ServerResponse> spaceRequestRoutes() {
        return GatewayRouterFunctions.route("space_request_service")
                // Rota para criar uma nova solicitação de espaço
                .route(RequestPredicates.POST("/api/space-request/createSpaceRequest"),
                        HandlerFunctions.http(SPACE_REQUEST_SERVICE_BASE_URL))

                // Rota para aceitar uma solicitação de espaço
                .route(RequestPredicates.PUT("/api/space-request/{id}/accept"),
                        HandlerFunctions.http(SPACE_REQUEST_SERVICE_BASE_URL))

                // Rota para recusar uma solicitação de espaço
                .route(RequestPredicates.PUT("/api/space-request/{id}/decline"),
                        HandlerFunctions.http(SPACE_REQUEST_SERVICE_BASE_URL))

                // Adicionando o Circuit Breaker para todas as rotas
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("spaceRequestServiceCircuitBreaker",
                        URI.create(FALLBACK_ROUTE)))
                .build();
    }

    // ===== Space Routes =====
    @Bean
    public RouterFunction<ServerResponse> spaceServiceRoutes() {
        return GatewayRouterFunctions.route("space_service")
                // Create Space
                .route(RequestPredicates.POST("/api/space-service/createSpace"),
                        HandlerFunctions.http(SPACE_SERVICE_BASE_URL))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("spaceServiceCreateCircuitBreaker",
                        URI.create(FALLBACK_ROUTE)))

                // Delete Space
                .route(RequestPredicates.DELETE("/api/space-service/deleteSpace/{id}"),
                        HandlerFunctions.http(SPACE_SERVICE_BASE_URL))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("spaceServiceDeleteCircuitBreaker",
                        URI.create(FALLBACK_ROUTE)))

                // Update Space
                .route(RequestPredicates.PUT("/api/space-service/updateSpace/{id}"),
                        HandlerFunctions.http(SPACE_SERVICE_BASE_URL))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("spaceServiceUpdateCircuitBreaker",
                        URI.create(FALLBACK_ROUTE)))

                // Get All Spaces
                .route(RequestPredicates.GET("/api/space-service/getAllSpaces"),
                        HandlerFunctions.http(SPACE_SERVICE_BASE_URL))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("spaceServiceGetAllCircuitBreaker",
                        URI.create(FALLBACK_ROUTE)))

                // Get Space by ID
                .route(RequestPredicates.GET("/api/space-service/getSpaceById/{spaceId}"),
                        HandlerFunctions.http(SPACE_SERVICE_BASE_URL))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("spaceServiceGetByIdCircuitBreaker",
                        URI.create(FALLBACK_ROUTE)))

                // Check if Space Exists by ID
                .route(RequestPredicates.GET("/api/space-service/existsById/{id}"),
                        HandlerFunctions.http(SPACE_SERVICE_BASE_URL))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("spaceServiceExistsByIdCircuitBreaker",
                        URI.create(FALLBACK_ROUTE)))

                // Get Space by Username
                .route(RequestPredicates.GET("/api/space-service/getSpaceByUsername/{username}"),
                        HandlerFunctions.http(SPACE_SERVICE_BASE_URL))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("spaceServiceGetByUsernameCircuitBreaker",
                        URI.create(FALLBACK_ROUTE)))
                .build();
    }

    // ===== User Routes =====
    @Bean
    public RouterFunction<ServerResponse> userServiceRoutes() {
        return GatewayRouterFunctions.route("user_service")
                // Login
                .route(RequestPredicates.POST("/api/user-service/login"),
                        HandlerFunctions.http(USER_SERVICE_BASE_URL))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("userServiceLoginCircuitBreaker",
                        URI.create(FALLBACK_ROUTE)))

                // Register
                .route(RequestPredicates.POST("/api/user-service/register"),
                        HandlerFunctions.http(USER_SERVICE_BASE_URL))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("userServiceRegisterCircuitBreaker",
                        URI.create(FALLBACK_ROUTE)))

                // Update User
                .route(RequestPredicates.PUT("/api/user-service/{id}"),
                        HandlerFunctions.http(USER_SERVICE_BASE_URL))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("userServiceUpdateCircuitBreaker",
                        URI.create(FALLBACK_ROUTE)))

                // Deactivate User
                .route(RequestPredicates.POST("/api/user-service/deactivate/{id}"),
                        HandlerFunctions.http(USER_SERVICE_BASE_URL))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("userServiceDeactivateCircuitBreaker",
                        URI.create(FALLBACK_ROUTE)))

                // Activate User
                .route(RequestPredicates.POST("/api/user-service/activate/{id}"),
                        HandlerFunctions.http(USER_SERVICE_BASE_URL))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("userServiceActivateCircuitBreaker",
                        URI.create(FALLBACK_ROUTE)))

                // Get All Users
                .route(RequestPredicates.GET("/api/user-service/getAllUsers"),
                        HandlerFunctions.http(USER_SERVICE_BASE_URL))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("userServiceGetAllCircuitBreaker",
                        URI.create(FALLBACK_ROUTE)))

                // Get User by ID
                .route(RequestPredicates.GET("/api/user-service/getUserById/{id}"),
                        HandlerFunctions.http(USER_SERVICE_BASE_URL))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("userServiceGetByIdCircuitBreaker",
                        URI.create(FALLBACK_ROUTE)))

                // Check if User Exists by ID
                .route(RequestPredicates.GET("/api/user-service/existsById/{id}"),
                        HandlerFunctions.http(USER_SERVICE_BASE_URL))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("userServiceExistsByIdCircuitBreaker",
                        URI.create(FALLBACK_ROUTE)))

                // Get User by Username
                .route(RequestPredicates.GET("/api/user-service/getUserByUsername/{username}"),
                        HandlerFunctions.http(USER_SERVICE_BASE_URL))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("userServiceGetByUsernameCircuitBreaker",
                        URI.create(FALLBACK_ROUTE)))
                .build();
    }

    // ===== FallBack Routes =====
    @Bean(name = "fallbackRoute")
    public RouterFunction<ServerResponse> fallbackRoute() {
        return GatewayRouterFunctions.route("fallback_route")
                .GET("/fallbackRoute", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Service Unavailable. Please try again later."))
                .build();
    }



}
