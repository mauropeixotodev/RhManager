package com.rhmanager.www.controller.cargo.response;

import java.util.List;

import com.rhmanager.www.controller.funcionario.response.FuncionarioResponse;


import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class CargoResponse {
	
	private Long id;
	
	private String nome;
	
	private String descricao;

	private Double salario;

	private List<FuncionarioResponse> funcionarios;
}
