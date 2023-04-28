package com.rhmanager.www.controller.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rhmanager.www.controller.usuario.request.UsuarioRequest;
import com.rhmanager.www.controller.usuario.response.UsuarioResponse;
import com.rhmanager.www.service.UsuarioService;

@RestController
@RequestMapping("/user")
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody @Validated UsuarioRequest UsuarioRequest)
			throws Exception {
		try {
			return ResponseEntity.ok(usuarioService.salvar(UsuarioRequest));
		} catch (Exception e) {
			throw new Exception("Erro ao criar o usuário: " + e);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponse> buscar(@PathVariable Long id) throws Exception {
		try {
			return ResponseEntity.ok(usuarioService.buscar(id));
		} catch (Exception e) {
			throw new Exception("Erro ao buscar usuário: " + e);
		}
	}

	@GetMapping
	public ResponseEntity<List<UsuarioResponse>> listar() throws Exception {
		try {
			return ResponseEntity.ok(usuarioService.listar());
		} catch (Exception e) {
			throw new Exception("Erro ao listar usuários: " + e);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioResponse> modificar(@PathVariable Long id, @RequestBody @Validated UsuarioRequest UsuarioRequest) throws Exception {
		try {
			return ResponseEntity.ok(usuarioService.atualizar(id, UsuarioRequest));
		} catch (Exception e) {
			throw new Exception("Erro ao atualizar usuário: " + e);
		}
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) throws Exception {
		try {
			usuarioService.deletar(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw new Exception("Erro ao deletar usuário: " + e);
		}
		
	}

}
