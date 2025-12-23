package com.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequetDto {
	private String email;
	private String password;
}
