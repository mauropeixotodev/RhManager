package com.rhmanager.www.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhmanager.www.controller.cargo.request.CargoRequest;
import com.rhmanager.www.controller.cargo.response.CargoResponse;
import com.rhmanager.www.conversor.CargoConversor;
import com.rhmanager.www.model.Cargo;
import com.rhmanager.www.repository.CargoRepository;

@Service
public class CargoService {
	@Autowired
	private CargoConversor cargoConversor;

	@Autowired
	private CargoRepository cargoRepository;

	public CargoResponse salvar(CargoRequest cargoRequest) {
		return cargoConversor.CargoBuildResponse(cargoRepository.save(cargoConversor.CargoBuild(cargoRequest)));
	}

	public CargoResponse buscar(Long id) {
		try {
			return cargoConversor.CargoBuildResponse(cargoRepository.findById(id).get());
		} catch (Exception e) {
			throw new Error("Cargo não encontrado" + e);
		}
	}

	public List<CargoResponse> listar() {
		return cargoConversor.CargoBuildResponse(cargoRepository.findAll());
	}
	@Transactional
	public CargoResponse atualizar(Long id, CargoRequest cargoRequest) {
		Optional<Cargo> CargoOpitional = cargoRepository.findById(id);
		if (CargoOpitional.isPresent()) {
			Cargo cargo = CargoOpitional.get();
			if (cargoRequest.getNome() != null) {
				cargo.setNome(cargoRequest.getNome());
			}
			if (cargoRequest.getDescricao() != null) {
				cargo.setDescricao(cargoRequest.getDescricao());
			}
			if (cargoRequest.getSalario() != null) {
				cargo.setSalario(cargoRequest.getSalario());;
			}
			return cargoConversor.CargoBuildResponse(cargo);
		}
		throw new Error("Funcionário não encontrado");

	}
	public void deletar(Long id) {
		try {
			cargoRepository.deleteById(id);
		} catch (Exception e) {
			throw new Error("Erro ao deletar usuário: " + e);
		}
	}

}
