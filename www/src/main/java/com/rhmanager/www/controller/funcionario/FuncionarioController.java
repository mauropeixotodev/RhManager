package com.rhmanager.www.controller.funcionario;

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

import com.rhmanager.www.controller.funcionario.request.FuncionarioRequest;
import com.rhmanager.www.controller.funcionario.response.FuncionarioResponse;
import com.rhmanager.www.service.FuncionarioService;

@RestController
@RequestMapping("/usuario")
public class FuncionarioController {
	@Autowired
	FuncionarioService funcionarioService;

	@PostMapping
	public ResponseEntity<FuncionarioResponse> cadastrar(@RequestBody @Validated FuncionarioRequest funcionarioRequest)
			throws Exception {
		try {
			return ResponseEntity.ok(funcionarioService.salvar(funcionarioRequest));
		} catch (Exception e) {
			throw new Exception("Erro ao criar o usuário: " + e);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<FuncionarioResponse> buscar(@PathVariable Long id) throws Exception {
		try {
			return ResponseEntity.ok(funcionarioService.buscar(id));
		} catch (Exception e) {
			throw new Exception("Erro ao buscar usuário: " + e);
		}
	}

	@GetMapping
	public ResponseEntity<List<FuncionarioResponse>> listar() throws Exception {
		try {
			return ResponseEntity.ok(funcionarioService.listar());
		} catch (Exception e) {
			throw new Exception("Erro ao listar usuários: " + e);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<FuncionarioResponse> modificar(@PathVariable Long id,
			@RequestBody @Validated FuncionarioRequest funcionarioRequest) throws Exception {
		try {
			return ResponseEntity.ok(funcionarioService.atualizar(id, funcionarioRequest));
		} catch (Exception e) {
			throw new Exception("Erro ao atualizar usuário: " + e);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) throws Exception {
		try {
			funcionarioService.deletar(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw new Exception("Erro ao deletar usuário: " + e);
		}

	}

}
