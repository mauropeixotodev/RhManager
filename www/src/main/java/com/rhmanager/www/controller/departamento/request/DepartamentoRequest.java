package com.rhmanager.www.controller.departamento.request;



import com.rhmanager.www.model.Funcionario;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Data
@NoArgsConstructor
public class DepartamentoRequest {

	@NonNull
	private String nome;
	@NonNull
	private String descricao;
	
	private Funcionario gestor;
}
