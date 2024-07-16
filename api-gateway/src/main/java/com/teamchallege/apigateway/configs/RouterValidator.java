package com.teamchallege.apigateway.configs;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class RouterValidator {

    public static final List<String> openEndpoints = List.of(
            "/api/open/auth/register",
            "/api/open/auth/login",
            "/api/open/category/all",
            "/api/open/book/{id}",
            "/api/open/book/_list",
            "/api/open/cart/{id}",
            "/api/open/cart/create-cart",
            "/api/open/cart/add-book",
            "/api/open/cart/remove-book",
            "/api/open/cart/clear-cart"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openEndpoints.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
