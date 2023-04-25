package com.rhmanager.www.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rhmanager.www.controller.cargo.request.CargoRequest;
import com.rhmanager.www.controller.cargo.response.CargoResponse;
import com.rhmanager.www.model.Cargo;

@Component
public class CargoConversor {

	public Cargo CargoBuild(CargoRequest cargoRequest) {
		return Cargo.builder().nome(cargoRequest.getNome()).descricao(cargoRequest.getDescricao())
				.salario(cargoRequest.getSalario()).build();
	}
	
	public CargoResponse CargoBuildResponse(Cargo cargo) {
		return CargoResponse.builder().id(cargo.getId()).nome(cargo.getNome()).descricao(cargo.getDescricao())
				.salario(cargo.getSalario()).build();
	}
	public List<CargoResponse> CargoBuildResponse(List<Cargo> cargos) {
		return cargos.stream().map(cargo -> CargoBuildResponse(cargo)).collect(Collectors.toList());
	}
}
