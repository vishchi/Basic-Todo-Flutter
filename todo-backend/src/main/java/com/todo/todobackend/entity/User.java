package com.todo.todobackend.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.CascadeType;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = "email", name="uniqueEmail")
})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String email;
	private String password;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userC", cascade = CascadeType.REMOVE)
	private Set<Category> categories = new HashSet<Category>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userT", cascade = CascadeType.REMOVE)
	private Set<Todo> todos = new HashSet<Todo>();

	public User() {
		super();
	}

	public User(Integer id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
