package com.pbyt.finance.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.pbyt.finance.enums.RoleEnum;
import com.pbyt.finance.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

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
            Map<String, String[]> parameterMap = request.getParameterMap();
            if (!parameterMap.isEmpty()) {
                for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                    String paramValues = entry.getValue()[0];
                    if (!paramValues.matches("^[a-zA-Z0-9:/.-]+$")) {
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid query");
                    }
                }
            }
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null) {
                if (authHeader.startsWith("Bearer ")) {
                    String token = authHeader.substring(7);
                    if (!jwtService.isTokenExpired(token)) {
                        String data = jwtService.extractAud(token);
                        String id = jwtService.extractId(token);
                        String roleList = id.split("-")[0];
                        if (SecurityContextHolder.getContext().getAuthentication() == null) {
                            Collection<Integer> authorities = objectMapper.readValue(roleList, Collection.class);
                            Collection<SimpleGrantedAuthority> roles = authorities
                                    .stream()
                                    .map(it->{
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
                        }
                    }
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(new Gson().toJson( e.getMessage()));
        }
    }
}
