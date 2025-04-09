package com.mirkoabozzi.api_gateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

    @Value("${product.service.uri}")
    private String productServiceUri;
    @Value("${order.service.uri}")
    private String orderServiceUri;
    @Value("${user.service.uri}")
    private String userServiceUri;

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return GatewayRouterFunctions.route("product_service")
                .route(RequestPredicates.path("/api/products/**"), HandlerFunctions.http(productServiceUri))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return GatewayRouterFunctions.route("order_service")
                .route(RequestPredicates.path("/api/orders/**"), HandlerFunctions.http(orderServiceUri))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userServiceAuthRoute() {
        return GatewayRouterFunctions.route("user_service")
                .route(RequestPredicates.path("/api/auth/**"), HandlerFunctions.http(userServiceUri))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userServiceRoute() {
        return GatewayRouterFunctions.route("user_service")
                .route(RequestPredicates.path("/api/users/**"), HandlerFunctions.http(userServiceUri))
                .build();
    }
}
