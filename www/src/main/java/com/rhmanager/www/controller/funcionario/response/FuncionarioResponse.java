package com.rhmanager.www.controller.funcionario.response;


import com.rhmanager.www.controller.cargo.response.CargoResponse;
import com.rhmanager.www.controller.departamento.response.DepartamentoResponse;


import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class FuncionarioResponse {
	private Long id;

	private String nome;

	private String sobrenome;

	private String nomeCompleto;

	private String cpf;

	private String nomeDaMae;

	private String nomeDoPai;

	private String rg;

	private String orgaoEmissor;

	private int idade;

	private String tipoDeContrato;

	private Double salario;

	private CargoResponse cargo;

	private DepartamentoResponse departamento;

	private boolean ativo;
}
