package com.rhmanager.www.security;


import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.rhmanager.www.configure.jwt.TokenTriangulador;
import com.rhmanager.www.model.Funcionario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;

	public TokenTriangulador gerarToken(Authentication authentication) {
		Funcionario logado = (Funcionario) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		   
		String token = Jwts.builder()
		.setIssuer("API Gestor de atendimento ")
		.setSubject(logado.getId().toString())
		.setIssuedAt(hoje)
		.setExpiration(dataExpiracao)
		.signWith(SignatureAlgorithm.HS256, secret)
		.compact();
		
		
		return TokenTriangulador.builder()
				.dataDeExpiracao(dataExpiracao)
				.token(token)
				.build();
	}
	
	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdFuncionario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
