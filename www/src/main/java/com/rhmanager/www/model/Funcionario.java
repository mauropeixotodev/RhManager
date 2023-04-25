package com.rhmanager.www.model;
import javax.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionario_seq")
	@SequenceGenerator(name = "funcionario_seq", sequenceName = "funcionario_seq", allocationSize = 1)
	@EqualsAndHashCode.Include
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String sobrenome;
	@Column(nullable = false)
	private String nomeCompleto;
	@Column(nullable = false)
	private String cpf;
	@Column(nullable = false)
	private String nomeDaMae;
	@Column(nullable = false)
	private String nomeDoPai;
	@Column(nullable = false)
	private String rg;
	@Column(nullable = false)
	private String orgaoEmissor;
	@Column(nullable = false)
	private int idade;
	@Column(nullable = false)
	private String tipoDeContrato;
	@Column(nullable = false)
	private Double salario;
	@ManyToOne
	@JoinColumn(name = "cargo_id", referencedColumnName = "id")
	private Cargo cargo;
	@ManyToOne
	@JoinColumn(name = "departamento_id", referencedColumnName = "id")
	private Departamento departamento;
	@Column(nullable = false)
	private boolean ativo;
	
	
}
