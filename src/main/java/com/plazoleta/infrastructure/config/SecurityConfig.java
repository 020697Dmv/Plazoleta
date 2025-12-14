package com.plazoleta.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
		return http.csrf(csrf -> csrf.disable())
		        .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
		        .authorizeHttpRequests(authRequest -> authRequest
		            .requestMatchers("/auth/**", "/api/v1/**", "/v3/api-docs/**", "/swagger-ui/**", "/h2-console/**")
		            .permitAll()
		            .anyRequest().authenticated() // Todo lo demás requiere autenticación
		        )
		        .build();
    }

}
