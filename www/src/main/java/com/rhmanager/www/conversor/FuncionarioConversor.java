package com.rhmanager.www.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

	public Funcionario funcionarioBuild(FuncionarioRequest funcionario) {
		return Funcionario.builder().nomeCompleto(funcionario.getNomeCompleto()).nome(funcionario.getNome())
				.sobrenome(funcionario.getSobrenome()).cpf(funcionario.getCpf()).rg(funcionario.getRg())
				.orgaoEmissor(funcionario.getOrgaoEmissor()).idade(funcionario.getIdade()).ativo(funcionario.isAtivo())
				.nomeDaMae(funcionario.getNomeDaMae()).nomeDoPai(funcionario.getNomeDoPai())
				.cargo(cargoRepository.findById(funcionario.getCargo()).get()).salario(funcionario.getSalario())
				.tipoDeContrato(funcionario.getTipoDeContrato())
				.departamento(departamentoRepository.findById(funcionario.getDepartamento()).get()).build();
	}

	public FuncionarioResponse funcionarioResponseBuild(Funcionario funcionario) {
		return FuncionarioResponse.builder().id(funcionario.getId()).nomeCompleto(funcionario.getNomeCompleto())
				.nome(funcionario.getNome()).sobrenome(funcionario.getSobrenome()).cpf(funcionario.getCpf())
				.rg(funcionario.getRg()).orgaoEmissor(funcionario.getOrgaoEmissor()).idade(funcionario.getIdade())
				.ativo(funcionario.isAtivo()).nomeDaMae(funcionario.getNomeDaMae())
				.nomeDoPai(funcionario.getNomeDoPai()).cargo(cargoBuildResponse(funcionario.getCargo()))
				.salario(funcionario.getSalario()).tipoDeContrato(funcionario.getTipoDeContrato())
				.departamento(departamentoResponseBuild(funcionario.getDepartamento())).build();
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
		return DepartamentoResponse.builder().id(departamento.getId()).descricao(departamento.getDescricao()).funcionarios(funcionarioResponseBuildNoDepartamentAndTitle(departamento.getFuncionarios())).build();
	}
	
	public List<DepartamentoResponse> departamentoResponseBuild(List<Departamento> departamentos) {
		return departamentos.stream().map(departamento -> departamentoResponseBuild(departamento))
				.collect(Collectors.toList());
	}
	
	public CargoResponse cargoBuildResponse(Cargo cargo) {
		return CargoResponse.builder().id(cargo.getId()).nome(cargo.getNome()).descricao(cargo.getDescricao())
				.salario(cargo.getSalario()).funcionarios(funcionarioResponseBuildNoDepartamentAndTitle(cargo.getFuncionarios())).build();
	}

	public List<CargoResponse> cargoBuildResponse(List<Cargo> cargos) {
		return cargos.stream().map(cargo -> cargoBuildResponse(cargo)).collect(Collectors.toList());
	}
}
