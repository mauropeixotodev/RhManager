package com.rhmanager.www.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rhmanager.www.model.Funcionario;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	Optional<Funcionario> findByNome(String nome);

	Optional<Funcionario> findByUsername(String username);
}