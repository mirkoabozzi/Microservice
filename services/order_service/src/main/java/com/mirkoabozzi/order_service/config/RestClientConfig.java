package com.mirkoabozzi.order_service.config;

import com.mirkoabozzi.order_service.client.ProductClient;
import com.mirkoabozzi.order_service.client.UserClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    @Value("${product.service.url}")
    private String productServiceURL;
    @Value("${user.service.url}")
    private String userServiceURL;

    @Bean
    public ProductClient productClient() {

        RestClient restClient = RestClient.builder().baseUrl(productServiceURL).build();

        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        return httpServiceProxyFactory.createClient(ProductClient.class);
    }

    @Bean
    public UserClient userClient() {

        RestClient restClient = RestClient.builder().baseUrl(userServiceURL).build();

        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        return httpServiceProxyFactory.createClient(UserClient.class);
    }
}

