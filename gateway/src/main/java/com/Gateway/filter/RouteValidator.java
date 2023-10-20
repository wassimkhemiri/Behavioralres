package com.Gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;
/*
import java.util.List;
*/

@Component
public class RouteValidator {
    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/token",
            "/eureka"
    );

    /*public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));*/

    public Predicate<ServerHttpRequest> isSecured =
            request -> {
                String path = request.getURI().getPath();
                return openApiEndpoints.stream().noneMatch(path::startsWith);
            };
}
