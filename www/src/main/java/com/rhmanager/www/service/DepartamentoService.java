package com.rhmanager.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhmanager.www.controller.departamento.request.DepartamentoRequest;
import com.rhmanager.www.controller.departamento.response.DepartamentoResponse;
import com.rhmanager.www.conversor.DepartamentoConversor;
import com.rhmanager.www.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
	@Autowired
	private DepartamentoConversor departementoConversor;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	public DepartamentoResponse salvar(DepartamentoRequest departamentoResquest) {
		return departementoConversor.DepartamentoResponseBuild(
				departamentoRepository.save(departementoConversor.DepartamentoBuild(departamentoResquest)));
	}

	public DepartamentoResponse buscar(Long id) {
		try {
			return departementoConversor.DepartamentoResponseBuild(departamentoRepository.findById(id).get());
		} catch (Exception e) {
			throw new Error("Departamento não encontrado" + e);
		}

	}

	public List<DepartamentoResponse> listar() {
		return departementoConversor.DepartamentoResponseBuild(departamentoRepository.findAll());
	}
}
