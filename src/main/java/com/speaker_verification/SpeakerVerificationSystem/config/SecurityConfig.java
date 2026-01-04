package com.speaker_verification.SpeakerVerificationSystem.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SecurityConfig extends OncePerRequestFilter {
    @Value("${security.api-key.get}")
    String GET_API_KEY;

    @Value("${security.api-key.post}")
    String POST_API_KEY;

    @Value("${security.api-key.put}")
    String PUT_API_KEY;

    @Value("${security.api-key.delete}")
    String DELETE_API_KEY;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String method = request.getMethod();
        String apiKey = request.getHeader("X-API-KEY");

        boolean isAuthenticated = switch (method) {
            case "GET" -> Objects.equals(apiKey, GET_API_KEY);
            case "POST" -> Objects.equals(apiKey, POST_API_KEY);
            case "PUT" -> Objects.equals(apiKey, PUT_API_KEY);
            case "DELETE" -> Objects.equals(apiKey, DELETE_API_KEY);
            default -> false;
        };

        if(!isAuthenticated) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("""
                { "message": "Invalid API key" }
            """);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
