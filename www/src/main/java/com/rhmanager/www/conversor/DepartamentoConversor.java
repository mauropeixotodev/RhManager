package com.rhmanager.www.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rhmanager.www.controller.departamento.request.DepartamentoRequest;
import com.rhmanager.www.model.Departamento;
import com.rhmanager.www.controller.departamento.response.DepartamentoResponse;

@Component
public class DepartamentoConversor {

	public Departamento DepartamentoBuild(DepartamentoRequest departamentoRequest) {
		return Departamento.builder().nome(departamentoRequest.getNome()).descricao(departamentoRequest.getDescricao())
				.gestor(((departamentoRequest.getGestor() != null)) ? departamentoRequest.getGestor() : null).build();
	}

	public DepartamentoResponse DepartamentoResponseBuild(Departamento departamento) {
	return DepartamentoResponse.builder().id(departamento.getId()).descricao(departamento.getDescricao()).build(); }

	public List<DepartamentoResponse> DepartamentoResponseBuild(List<Departamento> departamentos) {
		return departamentos.stream().map(departamento -> DepartamentoResponseBuild(departamento)).collect(Collectors.toList());}
}
