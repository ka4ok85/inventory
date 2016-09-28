package com.example.security;

public class JwtAuthenticationBadLoginResponse {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "JwtAuthenticationBadLoginResponse [message=" + message + "]";
	}
	
	
}
