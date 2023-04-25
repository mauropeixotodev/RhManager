package com.rhmanager.www.controller.cargo.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class CargoRequest {
	@NonNull
	private String nome;
	@NonNull
	private String descricao;
	@NonNull
	private Double salario;
}
