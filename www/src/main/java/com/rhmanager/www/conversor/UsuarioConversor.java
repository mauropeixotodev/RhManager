package com.rhmanager.www.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rhmanager.www.controller.usuario.request.UsuarioRequest;
import com.rhmanager.www.controller.usuario.response.UsuarioResponse;
import com.rhmanager.www.model.Usuario;

@Component
public class UsuarioConversor {

	
	public Usuario UsuarioBuild(UsuarioRequest usuarioRequest){
		
		return Usuario.builder().username(usuarioRequest.getUsername()).password(usuarioRequest.getPassword()).build();
	}
	
	public UsuarioResponse UsuarioReponseBuild(Usuario usuario){
		
		return UsuarioResponse.builder().id(usuario.getId()).username(usuario.getUsername()).password(usuario.getPassword()).build();
	}
	
	public List<UsuarioResponse> UsuarioReponseBuild(List<Usuario> usuarios){
		
		return usuarios.stream().map(usuario -> UsuarioReponseBuild(usuario)).collect(Collectors.toList());
	}
	
	
}
