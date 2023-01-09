package pl.eti.aui.kuprianowicz.michal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class Lab3Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Lab3Application.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder,
                                           @Value("${server.port}") String serverPort,
                                           @Value("${libraries.url}") String baseUrlLibraries,
                                           @Value("${books.url}") String baseUrlBooks) {
        return builder
                .routes()
                .route("libraries", r -> r
                        .host("localhost")
                        .and()
                        .path("/api/libraries/{libraryId}", "/api/libraries")
                        .uri(baseUrlLibraries))
                .route("books", r -> r
                        .host("localhost")
                        .and()
                        .path("/api/books", "/api/books/**", "/api/libraries/{libraryId}/books", "/api/libraries/{libraryId}/books/**")
                        .uri(baseUrlBooks))
                .build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {

        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        corsConfig.addAllowedHeader("*");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
