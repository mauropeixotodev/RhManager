package com.rhmanager.www.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rhmanager.www.controller.funcionario.request.FuncionarioRequest;
import com.rhmanager.www.controller.funcionario.response.FuncionarioResponse;
import com.rhmanager.www.model.Funcionario;
import com.rhmanager.www.repository.CargoRepository;
import com.rhmanager.www.repository.DepartamentoRepository;

@Component
public class FuncionarioConversor {
	@Autowired
	private DepartamentoRepository departamentoRepository;
	@Autowired
	private CargoRepository cargoRepository;
	@Autowired
	private DepartamentoConversor departementoConversor;
	@Autowired
	private CargoConversor cargoConversor;

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
				.nomeDoPai(funcionario.getNomeDoPai()).cargo(cargoConversor.CargoBuildResponse(funcionario.getCargo()))
				.salario(funcionario.getSalario()).tipoDeContrato(funcionario.getTipoDeContrato())
				.departamento(departementoConversor.DepartamentoResponseBuild(funcionario.getDepartamento())).build();
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
}
