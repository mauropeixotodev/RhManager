package com.rhmanager.www.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhmanager.www.controller.funcionario.request.FuncionarioRequest;
import com.rhmanager.www.controller.funcionario.response.FuncionarioResponse;
import com.rhmanager.www.conversor.FuncionarioConversor;
import com.rhmanager.www.model.Funcionario;
import com.rhmanager.www.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	@Autowired
	private FuncionarioConversor funcionarioConversor;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public FuncionarioResponse salvar(FuncionarioRequest funcionarioRequest) {
		return funcionarioConversor.funcionarioResponseBuild(
				funcionarioRepository.save(funcionarioConversor.funcionarioBuild(funcionarioRequest)));
	}

	public FuncionarioResponse buscar(Long id) {
		try {
			return funcionarioConversor.funcionarioResponseBuild(funcionarioRepository.findById(id).get());
		} catch (Exception e) {
			throw new Error("Funcionário não encontrado" + e);
		}

	}

	public List<FuncionarioResponse> listar() {
		return funcionarioConversor.funcionarioResponseBuild(funcionarioRepository.findAll());
	}

//public FuncionarioResponse atualizar(Long id, FuncionarioRequest funcionarioRequest) {
//		Optional<Funcionario> funcionarioOpitional = funcionarioRepository.findById(id);
//		if (funcionarioOpitional.isPresent()) {
//			Funcionario funcionario = funcionarioOpitional.get();
//			if (funcionarioRequest.getNome() != null) {
//				funcionario.setCpf(funcionarioRequest.getNome());
//			}
//			;
//			if (funcionarioRequest.getNome() != null) {
//				funcionario.setCpf(funcionarioRequest.getNome());
//			}
//			;
//			if (funcionarioRequest. != null) {
//				funcionario.setCpf(funcionarioRequest.getNome());
//			}
//			;
//			if (funcionarioRequest.getNome() != null) {
//				funcionario.setCpf(funcionarioRequest.getNome());
//			}
//			;
//			if (funcionarioRequest.getNome() != null) {
//				funcionario.setCpf(funcionarioRequest.getNome());
//			}
//			;
//			if (funcionarioRequest.getNome() != null) {
//				funcionario.setCpf(funcionarioRequest.getNome());
//			}
//			;
//			if (funcionarioRequest.getNome() != null) {
//				funcionario.setCpf(funcionarioRequest.getNome());
//			}
//			;
//			if (funcionarioRequest.getNome() != null) {
//				funcionario.setCpf(funcionarioRequest.getNome());
//			}
//			;
//			if (funcionarioRequest.getNome() != null) {
//				funcionario.setCpf(funcionarioRequest.getNome());
//			}
//			;
//			if (funcionarioRequest.getNome() != null) {
//				funcionario.setCpf(funcionarioRequest.getNome());
//			}
//			;
//		}
//
//		return null;
//	}
}
