package com.todo.todobackend.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.todo.todobackend.entity.Category;
import com.todo.todobackend.entity.Todo;
import com.todo.todobackend.entity.User;
import com.todo.todobackend.entity.input.InsertUserInput;
import com.todo.todobackend.entity.output.SignInUser;
import com.todo.todobackend.security.annotations.Unsecured;
import com.todo.todobackend.services.CategoryService;
import com.todo.todobackend.services.TodoService;
import com.todo.todobackend.services.UserService;
import com.todo.todobackend.utility.Jwt;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class QueryResolver implements GraphQLQueryResolver{
	@Autowired
	private TodoService todoService;
	
	@Autowired
	private CategoryService catService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private Jwt jwt;
	
	// User Queries
	@Unsecured
	public SignInUser user(InsertUserInput input) {
		System.out.println("Fetching User details");
		User user = userService.getUser(input.getEmail());
		if(user != null) {
			if(passwordEncoder.matches(input.getPassword(), user.getPassword())) {
				String token = jwt.generateToken(user); 
				SignInUser output = new SignInUser(user.getEmail(), token);
				return output;
			}
		}
		// TODO throw error
		return null;
		
	}
	
	public List<Todo> todos() {
		System.out.println("Fecthing all todos");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Todo> todos = todoService.getTodosByUserId(user.getId());
		for(Todo todo: todos) {
			System.out.println(todo);
		}
		return todos;
	}
	
	public Todo todo(Integer id) {
		System.out.println("Fetching todo");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Todo todo = todoService.getTodo(user.getId(), id);
		System.out.println(todo);
		return todo;
	}
	
	public List<Todo> todosByCat(String category) {
		System.out.println("Fetching todo by cat");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Category cat = catService.getCategoryByName(category, user.getId());
		List<Todo> todos = todoService.getTodosByCat(user.getId(), cat.getId());
		for(Todo todo: todos) {
			System.out.println(todo);
		}
		return todos;
		
	}
	
	public List<Category> categories() {
		System.out.println("Fetching all Categories");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Category> categories = catService.getCategories(user.getId());
		for(Category category: categories) {
			System.out.println(category);
		}
		return categories;
	}
	
	public Category category(Integer id) {
		System.out.println("Fetching category");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Category category = catService.getCategory(user.getId(), id);
		System.out.println(category);
		return category;
	}

}
