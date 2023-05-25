package com.rhmanager.www.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rhmanager.www.model.Funcionario;
import com.rhmanager.www.repository.FuncionarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {
	
	@Autowired
	private FuncionarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Funcionario> funcionario = repository.findByUsername(username);
		if (funcionario.isPresent()) {
			System.out.println("Senha aqui " +funcionario.get().getPassword());
			return funcionario.get();
		}
		
		throw new UsernameNotFoundException("Dados inválidos!");
	}

}