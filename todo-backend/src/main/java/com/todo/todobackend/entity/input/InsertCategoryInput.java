package com.todo.todobackend.entity.input;

public class InsertCategoryInput {
	private String name;

	public InsertCategoryInput(String name, String description) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
