package com.udea.skopetest.security;

public class JWTAuthResponseDTO {
	
	private String token;
	private String tipoDeToken = "Bearer";

	public JWTAuthResponseDTO(String token) {
		super();
		this.token = token;
	}

	public JWTAuthResponseDTO(String token, String tipoDeToken) {
		super();
		this.token = token;
		this.tipoDeToken = tipoDeToken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String tokenDeAcceso) {
		this.token = tokenDeAcceso;
	}

	public String getTipoDeToken() {
		return tipoDeToken;
	}

	public void setTipoDeToken(String tipoDeToken) {
		this.tipoDeToken = tipoDeToken;
	}

}
