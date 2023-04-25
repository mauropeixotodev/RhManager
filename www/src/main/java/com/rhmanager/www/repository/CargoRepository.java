package com.rhmanager.www.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rhmanager.www.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

	Optional<Cargo> findByNome(String nome);

}