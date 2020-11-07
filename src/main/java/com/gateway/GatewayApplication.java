package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
////                .route(r -> r.path("/currency-converter-feign/**")
////                        .uri("http://httpbin.org:80")
////                        .id("currencyConverter"))
//                .route(r -> r.path("/get")
//                        .uri("http://httpbin.org:80")
//                        .id("currencyConverter"))
//                .build();
//        
        //path("/currency-converter-feign/**")
        
        return builder.routes()
                .route(p -> p
                    .path("/currency-converter-feign/**")
                    .uri("http://localhost:8100"))
                .route(p -> p
                        .path("/forex/**")
                        .uri("lb://FOREX-SERVICE")) // Micro service name.. Eureka discovery
                .route(r -> r.path("/get") //currency-converter-feign
                		 .uri("http://httpbin.org:80"))
                .build();
    
    }
}
