package com.rhmanager.www.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Transactional
	public FuncionarioResponse atualizar(Long id, FuncionarioRequest funcionarioRequest) {
		Optional<Funcionario> funcionarioOpitional = funcionarioRepository.findById(id);
		if (funcionarioOpitional.isPresent()) {
			Funcionario funcionario = funcionarioOpitional.get();
			if (funcionarioRequest.getNome() != null) {
				funcionario.setNome(funcionarioRequest.getNome());
			}
			if (funcionarioRequest.getSobrenome() != null) {
				funcionario.setSobrenome(funcionarioRequest.getSobrenome());
			}
			if (funcionarioRequest.getNomeCompleto() != null) {
				funcionario.setNomeCompleto(funcionarioRequest.getNomeCompleto());
			}
			if (funcionarioRequest.getCpf() != null) {
				funcionario.setCpf(funcionarioRequest.getCpf());
			}
			if (funcionarioRequest.getNomeDaMae() != null) {
				funcionario.setNomeDaMae(funcionarioRequest.getNomeDaMae());
			}
			if (funcionarioRequest.getNomeDoPai() != null) {
				funcionario.setNomeDoPai(funcionarioRequest.getNomeDoPai());
			}
			if (funcionarioRequest.getRg() != null) {
				funcionario.setRg(funcionarioRequest.getRg());
			}
			if (funcionarioRequest.getOrgaoEmissor() != null) {
				funcionario.setOrgaoEmissor(funcionarioRequest.getOrgaoEmissor());
			}
			if (funcionarioRequest.getIdade() != 0) {
				funcionario.setIdade(funcionarioRequest.getIdade());
			}
			if (funcionarioRequest.getTipoDeContrato() != null) {
				funcionario.setTipoDeContrato(funcionarioRequest.getTipoDeContrato());
			}
			if (funcionarioRequest.getSalario() != null) {
				funcionario.setSalario(funcionarioRequest.getSalario());
			}
			if (funcionarioRequest.getUsername() != null) {
				funcionario.setUsername(funcionarioRequest.getUsername());
			}
			if (funcionarioRequest.getPassword() != null) {
				funcionario.setPassword(new BCryptPasswordEncoder().encode(funcionarioRequest.getPassword()));
			}
			return funcionarioConversor.funcionarioResponseBuild(funcionario);
		}
		throw new Error("Funcionário não encontrado");

	}

	public void deletar(Long id) {

		Optional<Funcionario> funcionarioOpitional = funcionarioRepository.findById(id);
		if (funcionarioOpitional.isPresent()) {
			funcionarioRepository.deleteById(id);
		} else {
			throw new Error("Funcionário não encontrado");
		}

	}

}
