package com.rhmanager.www.controller.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhmanager.www.configure.jwt.TokenResponse;
import com.rhmanager.www.configure.jwt.TokenTriangulador;
import com.rhmanager.www.controller.funcionario.request.FuncionarioRequest;
import com.rhmanager.www.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenResponse> autenticar(@RequestBody @Validated FuncionarioRequest funcionarioRequest)
			throws Exception {
		UsernamePasswordAuthenticationToken dadosLogin = funcionarioRequest.converter();

		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			TokenTriangulador token = tokenService.gerarToken(authentication);

			TokenResponse tokenResponse = TokenResponse.builder().dataDeExpiracao(token.getDataDeExpiracao())
					.token(token.getToken()).type("Bearer").build();

			return ResponseEntity.ok(tokenResponse);
		} catch (AuthenticationException e) {
			throw new Exception("E-mail ou senha inválidos");
		}
	}

}
