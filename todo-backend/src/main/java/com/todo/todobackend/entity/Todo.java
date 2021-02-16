package com.todo.todobackend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {"userId", "title"}, name = "uniqueTodoPerUser"),
		@UniqueConstraint(columnNames = {"catId", "title"}, name = "uniqueTodoPerCat")
})
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String description;
	@ManyToOne
	@JoinColumn(name = "userId", nullable = true)
	private User user;
	@ManyToOne
	@JoinColumn(name = "catId", nullable = true)
	private Category category;

	public Todo() {
	}

	public Todo(Integer id, String title, String description, User user, Category category) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.user = user;
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + title + ", description=" + description + ", category=" + category + "]";
	}

}
