package com.rhmanager.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhmanager.www.controller.usuario.request.UsuarioRequest;
import com.rhmanager.www.controller.usuario.response.UsuarioResponse;
import com.rhmanager.www.conversor.UsuarioConversor;
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

	public List<UsuarioResponse> listar() {
		return usuarioConversor.UsuarioReponseBuild(usuarioRepository.findAll());
	}

	public void deletar(Long id) {

		usuarioRepository.deleteById(id);
	}
}
