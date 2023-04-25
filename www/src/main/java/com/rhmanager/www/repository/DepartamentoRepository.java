package com.rhmanager.www.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rhmanager.www.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

	Optional<Departamento> findByNome(String nome);

}