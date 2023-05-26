package com.rhmanager.www.controller.cargo;

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

import com.rhmanager.www.controller.cargo.request.CargoRequest;
import com.rhmanager.www.controller.cargo.response.CargoResponse;
import com.rhmanager.www.service.CargoService;

public class CargoController {
	@Autowired
	CargoService cargoService;

	@PostMapping
	public ResponseEntity<CargoResponse> cadastrar(@RequestBody @Validated CargoRequest cargoRequest)
			throws Exception {
		try {
			return ResponseEntity.ok(cargoService.salvar(cargoRequest));
		} catch (Exception e) {
			throw new Exception("Erro ao criar o cargo: " + e);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<CargoResponse> buscar(@PathVariable Long id) throws Exception {
		try {
			return ResponseEntity.ok(cargoService.buscar(id));
		} catch (Exception e) {
			throw new Exception("Erro ao buscar cargo: " + e);
		}
	}

	@GetMapping
	public ResponseEntity<List<CargoResponse>> listar() throws Exception {
		try {
			return ResponseEntity.ok(cargoService.listar());
		} catch (Exception e) {
			throw new Exception("Erro ao listar cargos: " + e);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<CargoResponse> modificar(@PathVariable Long id,
			@RequestBody @Validated CargoRequest cargoRequest) throws Exception {
		try {
			return ResponseEntity.ok(cargoService.atualizar(id, cargoRequest));
		} catch (Exception e) {
			throw new Exception("Erro ao atualizar cargo: " + e);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) throws Exception {
		try {
			cargoService.deletar(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw new Exception("Erro ao deletar cargo: " + e);
		}

	}
}
