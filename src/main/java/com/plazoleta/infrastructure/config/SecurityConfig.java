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

import com.plazoleta.infrastructure.out.jpa.auth.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
	    return http.csrf(csrf -> csrf.disable())
	            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
	            .authorizeHttpRequests(auth -> auth
	            	    .requestMatchers("/api/v1/Auth/login").permitAll()
	            	    .requestMatchers("/api/v1/User/saveUser").hasAnyAuthority("ADMINISTRATOR", "OWNER","EMPLOYEE")
	                    .requestMatchers("/api/v1/User/**").hasAuthority("ADMINISTRATOR")
	                    .requestMatchers("/api/v1/Restaurant/saveRestaurant").hasAuthority("ADMINISTRATOR")
	                    .requestMatchers("/api/v1/Restaurant/listRestaurants").hasAuthority("CLIENT")
	                    .requestMatchers("/api/v1/Plate/savePlate").hasAuthority("OWNER")
	                    .requestMatchers("/api/v1/Plate/updatePlate").hasAuthority("OWNER")
	                    .requestMatchers("/api/v1/Plate/updateActivePlate").hasAuthority("OWNER")
	                    .requestMatchers("/api/v1/Plate/listPlates").hasAuthority("CLIENT")
	                    .requestMatchers("/api/v1/Orders/saveOrder").hasAuthority("CLIENT")
	                    .requestMatchers("/api/v1/Orders/listOrders").hasAuthority("EMPLOYEE")
	                    .requestMatchers("/api/v1/Orders/sendSmsNotify").hasAuthority("EMPLOYEE")
	                    .requestMatchers("/api/v1/Orders/updateDeliveredOrder").hasAuthority("EMPLOYEE")
	                    .requestMatchers("/api/v1/Orders/cancelOrder").hasAuthority("CLIENT")
	                    .requestMatchers("/api/v1/Plate/**").hasAuthority("OWNER")
	            	    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/h2-console/**").permitAll()
	            	    .anyRequest().authenticated()
	            	)
	            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
	            .build();
	}

}
