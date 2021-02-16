package com.todo.todobackend.entity.output;

public class SignInUser {
	private String email;
	private String token;

	public SignInUser(String email, String token) {
		super();
		this.email = email;
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public String getToken() {
		return token;
	}

}
