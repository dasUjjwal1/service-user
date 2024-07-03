package com.pbyt.finance.config;

import com.pbyt.finance.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FilterConfig extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String path = request.getServletPath();
            if (path.startsWith("/api/v1/app")) {
                String authHeader = request.getHeader("Authorization");
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    String token = authHeader.substring(7);
                    String userId = jwtService.extractId(token);
                    request.setAttribute("userId", userId);
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(400, "Invalid Request");
                }
            } else {
                filterChain.doFilter(request, response);
            }
        } catch (Exception e) {
            response.sendError(400, e.getMessage());
        }
    }

}
