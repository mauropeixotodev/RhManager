package com.rhmanager.www.controller.funcionario.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class FuncionarioRequest {
	@NonNull
	private String nome;
	@NonNull
	private String sobrenome;
	@NonNull
	private String nomeCompleto;
	@NonNull
	private String cpf;
	@NonNull
	private String nomeDaMae;
	@NonNull
	private String nomeDoPai;
	@NonNull
	private String rg;
	@NonNull
	private String orgaoEmissor;

	private int idade;
	@NonNull
	private String tipoDeContrato;
	@NonNull
	private Double salario;

	private Long cargo;

	private Long departamento;

	private boolean ativo;
	@NonNull
	private String username;
	@NonNull
	private String password;

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(this.username, this.password);
	}
}
