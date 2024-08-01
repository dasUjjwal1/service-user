package com.pbyt.finance.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbyt.finance.exception.TokenNull;
import com.pbyt.finance.service.JwtService;
import com.pbyt.finance.util.AuthoritiesConverter;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    public JwtService jwtService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            if (authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                String data = jwtService.extractAud(token);
                Collection<? extends GrantedAuthority> authorities =
                        objectMapper.readValue(data, Collection.class);
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    if (!jwtService.isTokenExpired(token)) {
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(null, null, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }
        }
        filterChain.doFilter(request, response);

    }
}
