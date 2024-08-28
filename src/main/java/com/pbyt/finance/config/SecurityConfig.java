package com.pbyt.finance.config;

import com.pbyt.finance.enums.RoleEnum;
import com.pbyt.finance.exception.AccessDeniedHandlerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JWTAuthFilter jwtAuthFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request) -> {
                    request.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/api/v1/auth/**").permitAll();
                    request.requestMatchers("/api/v1/app/user/**").hasRole(RoleEnum.ADMIN.name());
                    request.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());
        http.exceptionHandling(exception -> exception
                .accessDeniedHandler(new AccessDeniedHandlerImpl())
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        );
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}
