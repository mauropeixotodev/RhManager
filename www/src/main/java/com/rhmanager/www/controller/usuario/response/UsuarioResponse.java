package com.rhmanager.www.controller.usuario.response;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UsuarioResponse {

	private Long id;
	
	private String username;

	private String password;



}