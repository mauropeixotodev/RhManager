package com.rhmanager.www.configure.jwt;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponse {

	private String type;

	private String token;

	private Date dataDeExpiracao;
}