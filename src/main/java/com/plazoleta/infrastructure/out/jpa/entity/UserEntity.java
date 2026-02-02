package com.plazoleta.infrastructure.out.jpa.entity;


import com.plazoleta.infrastructure.out.jpa.util.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity implements UserDetails{
	
	@Id
	@Column(name="identity_document")
	private Long id;
	
	@Column()
	private String name;
	
	@Column()
	private String lastname;
	
	@Column()
	private String phone;

	@Column(name="birthdate")
	private LocalDate birthdate;
	
	@Column()
	private String email;
	
	@Column()
	private String password;
	
	@Column()
	@Enumerated(EnumType.STRING) 
	private Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		   if(role == null) return null;

	        if(role.getPermissions() == null) return null;

	        return role.getPermissions().stream()
	                .map(each -> each.name())
	                .map(each -> new SimpleGrantedAuthority(each))
	                .toList();
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
