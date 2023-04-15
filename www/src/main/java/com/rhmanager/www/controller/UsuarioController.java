package com.rhmanager.www.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.rhmanager.www.controller.usuario.request.UsuarioRequest;
import com.rhmanager.www.controller.usuario.response.UsuarioResponse;
import com.rhmanager.www.model.Usuario;
import com.rhmanager.www.repository.UsuarioRepository;
@RestController
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
	private UsuarioRepository usuarioreposiroty;
	
	@PostMapping("")
	public ResponseEntity<UsuarioResponse> cadastro(@RequestBody @Validated UsuarioRequest usuarioRequest,
			UriComponentsBuilder uriBuilder) throws Exception {
		Usuario usuario = usuarioreposiroty.save(Usuario.builder().username(usuarioRequest.getUsername()).password(new BCryptPasswordEncoder().encode(usuarioRequest.getPassword())).build());
		URI uri = uriBuilder.path("/categoria/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(UsuarioResponse.builder().id(usuario.getId()).username(usuario.getUsername()).password(usuario.getPassword()).build());
	}
	@GetMapping("/teste")
	public String cadastro() {
		return "OK foi mae";
	}
	
	
}
