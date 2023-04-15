package com.rhmanager.www.controller.usuario.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

;

@Data
@NoArgsConstructor
public class UsuarioRequest {
	@NonNull
	private String username;
	@NonNull
	private String password;

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(this.username, this.password);
	}

}