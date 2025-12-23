package com.plazoleta.infrastructure.out.jpa.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.plazoleta.application.dto.request.LoginRequetDto;
import com.plazoleta.application.dto.response.AuthRespondeDto;
import com.plazoleta.infrastructure.out.jpa.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final JwtService jwtService;
	private final IUserRepository userRepository;
		private final AuthenticationManager authenticationManager;

	
	public AuthRespondeDto login(LoginRequetDto request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		UserDetails user = userRepository.findByEmail(request.getEmail()).orElseThrow();
		String token = jwtService.getToken(user);
		return AuthRespondeDto.builder().token(token).build();
	}

}
