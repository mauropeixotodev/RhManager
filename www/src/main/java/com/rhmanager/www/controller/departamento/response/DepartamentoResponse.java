package com.rhmanager.www.controller.departamento.response;

import java.util.List;

import com.rhmanager.www.controller.funcionario.response.FuncionarioResponse;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class DepartamentoResponse {

	private Long id;
	
	private String nome;
	
	private String descricao;
	
	private FuncionarioResponse gestor;
	
	private List<FuncionarioResponse> funcionarios;
}
