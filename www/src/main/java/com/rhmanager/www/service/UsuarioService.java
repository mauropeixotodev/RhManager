package com.rhmanager.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rhmanager.www.controller.usuario.request.UsuarioRequest;
import com.rhmanager.www.controller.usuario.response.UsuarioResponse;
import com.rhmanager.www.conversor.UsuarioConversor;
import com.rhmanager.www.model.Usuario;
import com.rhmanager.www.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioConversor usuarioConversor;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioResponse salvar(UsuarioRequest UsuarioRequest) {
		return usuarioConversor
				.UsuarioReponseBuild(usuarioRepository.save(usuarioConversor.UsuarioBuild(UsuarioRequest)));
	}

	public UsuarioResponse buscar(Long id) {
		try {
			return usuarioConversor.UsuarioReponseBuild(usuarioRepository.findById(id).get());
		} catch (Exception e) {
			throw new Error("Usuário não encontrado" + e);
		}

	}

	@Transactional
	public UsuarioResponse atualizar(Long id, UsuarioRequest usuarioRequest) {
		Usuario usuario = usuarioRepository.findById(id).get();
		usuario.setUsername((usuario.getUsername() != null) ? usuario.getUsername() : null);
		usuario.setPassword(
				(usuario.getPassword() != null) ? new BCryptPasswordEncoder().encode(usuario.getPassword()) : null);
		return usuarioConversor.UsuarioReponseBuild(usuario);
	}

	public List<UsuarioResponse> listar() {
		return usuarioConversor.UsuarioReponseBuild(usuarioRepository.findAll());
	}

	public void deletar(Long id) {

		usuarioRepository.deleteById(id);
	}
}
