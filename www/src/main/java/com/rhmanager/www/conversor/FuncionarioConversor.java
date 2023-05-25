package com.rhmanager.www.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.rhmanager.www.controller.cargo.response.CargoResponse;
import com.rhmanager.www.controller.departamento.request.DepartamentoRequest;
import com.rhmanager.www.controller.departamento.response.DepartamentoResponse;
import com.rhmanager.www.controller.funcionario.request.FuncionarioRequest;
import com.rhmanager.www.controller.funcionario.response.FuncionarioResponse;
import com.rhmanager.www.model.Cargo;
import com.rhmanager.www.model.Departamento;
import com.rhmanager.www.model.Funcionario;
import com.rhmanager.www.repository.CargoRepository;
import com.rhmanager.www.repository.DepartamentoRepository;

@Component
public class FuncionarioConversor {
	@Autowired
	private DepartamentoRepository departamentoRepository;
	@Autowired
	private CargoRepository cargoRepository;

	public Funcionario funcionarioBuild(FuncionarioRequest funcionarioRequest) {

		Funcionario funcionario = Funcionario.builder().nomeCompleto(funcionarioRequest.getNomeCompleto())
				.nome(funcionarioRequest.getNome()).sobrenome(funcionarioRequest.getSobrenome())
				.cpf(funcionarioRequest.getCpf()).rg(funcionarioRequest.getRg())
				.orgaoEmissor(funcionarioRequest.getOrgaoEmissor()).idade(funcionarioRequest.getIdade())
				.ativo(funcionarioRequest.isAtivo()).nomeDaMae(funcionarioRequest.getNomeDaMae())
				.nomeDoPai(funcionarioRequest.getNomeDoPai()).salario(funcionarioRequest.getSalario())
				.tipoDeContrato(funcionarioRequest.getTipoDeContrato()).username(funcionarioRequest.getUsername())
				.password(new BCryptPasswordEncoder().encode(funcionarioRequest.getPassword())).build();
		if (funcionarioRequest.getCargo() != null)
			funcionario.setCargo(cargoRepository.findById(funcionarioRequest.getCargo()).get());
		if (funcionarioRequest.getDepartamento() != null)
			funcionario.setDepartamento(departamentoRepository.findById(funcionarioRequest.getDepartamento()).get());
		return funcionario;

	}

	public FuncionarioResponse funcionarioResponseBuild(Funcionario funcionario) {
		FuncionarioResponse funcionarioResponse = FuncionarioResponse.builder().id(funcionario.getId()).nomeCompleto(funcionario.getNomeCompleto())
				.nome(funcionario.getNome()).sobrenome(funcionario.getSobrenome()).cpf(funcionario.getCpf())
				.rg(funcionario.getRg()).orgaoEmissor(funcionario.getOrgaoEmissor()).idade(funcionario.getIdade())
				.ativo(funcionario.isAtivo()).nomeDaMae(funcionario.getNomeDaMae())
				.nomeDoPai(funcionario.getNomeDoPai()).salario(funcionario.getSalario())
				.tipoDeContrato(funcionario.getTipoDeContrato())
				.build();
		
		if (funcionario.getCargo() != null)
			funcionarioResponse.setCargo(cargoBuildResponse(funcionario.getCargo()));
		if (funcionario.getDepartamento() != null)funcionarioResponse.setDepartamento(departamentoResponseBuild(funcionario.getDepartamento()));
		return funcionarioResponse;

	}

	public List<FuncionarioResponse> funcionarioResponseBuild(List<Funcionario> funcionarios) {
		return funcionarios.stream().map(e -> funcionarioResponseBuild(e)).collect(Collectors.toList());
	}

	public FuncionarioResponse funcionarioResponseBuildNoDepartamentAndTitle(Funcionario funcionario) {
		return FuncionarioResponse.builder().id(funcionario.getId()).nomeCompleto(funcionario.getNomeCompleto())
				.nome(funcionario.getNome()).sobrenome(funcionario.getSobrenome()).cpf(funcionario.getCpf())
				.rg(funcionario.getRg()).orgaoEmissor(funcionario.getOrgaoEmissor()).idade(funcionario.getIdade())
				.ativo(funcionario.isAtivo()).nomeDaMae(funcionario.getNomeDaMae())
				.nomeDoPai(funcionario.getNomeDoPai()).salario(funcionario.getSalario())
				.tipoDeContrato(funcionario.getTipoDeContrato()).build();
	}

	public List<FuncionarioResponse> funcionarioResponseBuildNoDepartamentAndTitle(List<Funcionario> funcionarios) {
		return funcionarios.stream().map(e -> funcionarioResponseBuildNoDepartamentAndTitle(e))
				.collect(Collectors.toList());
	}

	public DepartamentoResponse departamentoResponseBuild(Departamento departamento) {
		return DepartamentoResponse.builder().id(departamento.getId()).descricao(departamento.getDescricao())
				.funcionarios(funcionarioResponseBuildNoDepartamentAndTitle(departamento.getFuncionarios())).build();
	}

	public List<DepartamentoResponse> departamentoResponseBuild(List<Departamento> departamentos) {
		return departamentos.stream().map(departamento -> departamentoResponseBuild(departamento))
				.collect(Collectors.toList());
	}

	public CargoResponse cargoBuildResponse(Cargo cargo) {
		return CargoResponse.builder().id(cargo.getId()).nome(cargo.getNome()).descricao(cargo.getDescricao())
				.salario(cargo.getSalario())
				.funcionarios(funcionarioResponseBuildNoDepartamentAndTitle(cargo.getFuncionarios())).build();
	}

	public List<CargoResponse> cargoBuildResponse(List<Cargo> cargos) {
		return cargos.stream().map(cargo -> cargoBuildResponse(cargo)).collect(Collectors.toList());
	}
}
