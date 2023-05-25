package com.rhmanager.www.model;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Funcionario implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;
	@ManyToOne
	@JoinColumn(name = "cargo_id", referencedColumnName = "id")
	private Cargo cargo;
	@ManyToOne
	@JoinColumn(name = "departamento_id", referencedColumnName = "id")
	private Departamento departamento;
	
	@Column(nullable = false)
	private boolean ativo;
	
	private List<String> perfil;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
