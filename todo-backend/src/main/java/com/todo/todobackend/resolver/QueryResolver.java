package com.todo.todobackend.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.todo.todobackend.entity.Category;
import com.todo.todobackend.entity.Todo;
import com.todo.todobackend.repository.CategoryRepository;
import com.todo.todobackend.repository.TodoRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class QueryResolver implements GraphQLQueryResolver{
	@Autowired
	TodoRepository todoRepository;
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Todo> todos() {
		System.out.println("Fecthing all todos");
		List<Todo> todos = todoRepository.findAll();
		for(Todo todo: todos) {
			System.out.println(todo);
		}
		return todos;
	}
	
	public Todo todo(Integer id) {
		System.out.println("Fetching todo");
		Todo todo = todoRepository.findById(id).orElse(null);
		System.out.println(todo);
		return todo;
	}
	
	public List<Category> categories() {
		System.out.println("Fetching all Categories");
		List<Category> categories = categoryRepository.findAll();
		for(Category category: categories) {
			System.out.println(category);
		}
		return categories;
	}
	
	public Category category(Integer id) {
		System.out.println("Fetching category");
		Category category = categoryRepository.findById(id).orElse(null);
		System.out.println(category);
		return category;
	}

}
