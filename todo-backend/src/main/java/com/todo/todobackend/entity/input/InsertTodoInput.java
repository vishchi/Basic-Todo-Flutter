package com.todo.todobackend.entity.input;

public class InsertTodoInput {
	private String title;
	private String description;
	private String category;

	public InsertTodoInput(String title, String description, String category) {
		this.title = title;
		this.description = description;
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getCategory() {
		return category;
	}

}
