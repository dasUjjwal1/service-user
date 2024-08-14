package com.pbyt.finance.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbyt.finance.enums.RoleEnum;
import com.pbyt.finance.exception.TokenNull;
import com.pbyt.finance.service.JwtService;
import com.pbyt.finance.util.AuthoritiesConverter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

@Component
@Slf4j
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    public JwtService jwtService;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            if (!parameterMap.isEmpty()){
                for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                    String paramValues = entry.getValue()[0];
                    if (!paramValues.matches("^[a-zA-Z0-9:/.-]+$")){
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        response.sendError(HttpServletResponse.SC_FORBIDDEN,"Invalid query");
                    }
                }
            }
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null) {
                if (authHeader.startsWith("Bearer ")) {
                    String token = authHeader.substring(7);
                    String data = jwtService.extractAud(token);
                    String id = jwtService.extractId(token);
                    String roleList = id.split("-")[0];
                    ObjectMapper mapper = new ObjectMapper();
                    Collection<GrantedAuthority> authorities = mapper.readValue(roleList, Collection.class)
                            .stream()
                            .map(it -> {
                                String role = switch (it.toString()) {
                                    case "0" -> RoleEnum.ADMIN.name();
                                    case "1" -> RoleEnum.ZM.name();
                                    case "2" -> RoleEnum.RSM.name();
                                    case "3" -> RoleEnum.RM.name();
                                    default -> throw new IllegalStateException("Unexpected value: " + it.toString());
                                };
                                return new SimpleGrantedAuthority("ROLE_"+role);
                            })
                            .toList();
                    log.info("Role--{}",authorities);
                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        if (!jwtService.isTokenExpired(token)) {
                            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, null, authorities);
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                        }
                    }

                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }
}
