package com.rhmanager.www.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rhmanager.www.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByUsername(String username);

}