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
	            	    .requestMatchers("/api/v1/Employee/saveEmployee").hasAuthority("CREATE_ONE_EMPLOYEE")
	            	    .requestMatchers("/api/v1/User/saveUser").hasAuthority("CREATE_ONE_USERS")
	                    .requestMatchers("/api/v1/User/**").hasAuthority("READ_ALL_USERS")
	                    .requestMatchers("/api/v1/Restaurant/saveRestaurant").hasAuthority("CREATE_ONE_RESTAURANT")
	                    .requestMatchers("/api/v1/Restaurant/**").hasAuthority("READ_ALL_RESTAURANT")
	                    .requestMatchers("/api/v1/Plate/savePlate").hasAuthority("CREATE_ONE_PLATE")
	                    .requestMatchers("/api/v1/Plate/updatePlate").hasAuthority("CREATE_ONE_PLATE")
	                    .requestMatchers("/api/v1/Plate/**").hasAuthority("READ_ALL_PLATE")
	            	    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/h2-console/**").permitAll()
	            	    .anyRequest().authenticated()
	            	)
	            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
	            .build();
	}

}
