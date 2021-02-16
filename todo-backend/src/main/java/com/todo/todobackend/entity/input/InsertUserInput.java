package com.todo.todobackend.entity.input;

public class InsertUserInput {
	private String email;
	private String password;

	public InsertUserInput(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}
