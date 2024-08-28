package com.pbyt.finance.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbyt.finance.enums.RoleEnum;
import com.pbyt.finance.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component
@Slf4j
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    public JwtService jwtService;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null) {
                if (authHeader.startsWith("Bearer ")) {
                    String token = authHeader.substring(7);
                    if (!jwtService.isTokenExpired(token)) {
                        String data = jwtService.extractAud(token);
                        String id = jwtService.extractId(token);
                        List<String> payload = List.of(id.split("-"));
                        String roleList = payload.get(0);
                        String userId = payload.get(1);
                        if (SecurityContextHolder.getContext().getAuthentication() == null) {
                            Collection<Integer> authorities = objectMapper.readValue(roleList, Collection.class);
                            Collection<SimpleGrantedAuthority> roles = authorities
                                    .stream()
                                    .map(it -> {
                                        String role = switch (it) {
                                            case 0 -> RoleEnum.ADMIN.name();
                                            case 1 -> RoleEnum.ZM.name();
                                            case 2 -> RoleEnum.RSM.name();
                                            case 3 -> RoleEnum.RM.name();
                                            default -> RoleEnum.USER.name();
                                        };
                                        return new SimpleGrantedAuthority("ROLE_" + role);
                                    })
                                    .toList();
                            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, null, roles);
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                            request.setAttribute("data",data);
                            request.setAttribute("id",userId);
                        }
                    }
                }
            }

        } catch (Exception jwtException) {
            request.setAttribute("exception", jwtException);
        }
        filterChain.doFilter(request, response);
    }
}
