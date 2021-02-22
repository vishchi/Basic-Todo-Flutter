package com.todo.todobackend.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.todo.todobackend.entity.Category;
import com.todo.todobackend.entity.Todo;
import com.todo.todobackend.entity.User;
import com.todo.todobackend.entity.input.InsertCategoryInput;
import com.todo.todobackend.entity.input.InsertTodoInput;
import com.todo.todobackend.entity.input.InsertUserInput;
import com.todo.todobackend.entity.output.SignInUser;
import com.todo.todobackend.security.annotations.Unsecured;
import com.todo.todobackend.services.CategoryService;
import com.todo.todobackend.services.TodoService;
import com.todo.todobackend.services.UserService;
import com.todo.todobackend.utility.Jwt;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class MutationResolver implements GraphQLMutationResolver {

	@Autowired
	private TodoService todoService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private Jwt jwt;

	// User Mutations
	@Unsecured
	public SignInUser insertUser(InsertUserInput input) {
		System.out.println("Inserting User");
		User user = userService.insertUser(input.getEmail(), passwordEncoder.encode(input.getPassword()));
		if(user == null) {
			// TODO throw exception
		}
		String token = jwt.generateToken(user); 
		SignInUser output = new SignInUser(user.getEmail(), token);
		return output;
	}

	// Insert Mutations
	public Todo insertTodo(InsertTodoInput input) {
		System.out.println("Inserting todo");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Category category = categoryService.getCategoryByName(input.getCategory(), user.getId());
		return todoService.insertTodo(input.getTitle(), input.getDescription(), category, user);
	}

	public Category insertCategory(InsertCategoryInput input) {
		System.out.println("Inserting category");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(user);
		return categoryService.insertCategory(input.getName(), user);
	}

	// Update Mutations
	public Todo updateTodo(Integer id, InsertTodoInput input) {
		System.out.println("Updating todo");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Category category = categoryService.getCategoryByName(input.getCategory(), user.getId());
		Todo todo = todoService.getTodo(user.getId(), id);
		return todoService.updateTodo(todo, input.getTitle(), input.getDescription(), category);

	}

	public Category updateCategory(Integer id, InsertCategoryInput input) {
		System.out.println("Updating category");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Category category = categoryService.getCategory(user.getId(), id);
		return categoryService.updateCategory(category, input.getName());
	}

	// delete Mutations
	public Todo deleteTodo(Integer id) {
		System.out.println("deleting todo");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Todo todo = todoService.getTodo(user.getId(), id);
		todoService.deleteTodo(todo);
		return todo;
	}

	public Category deleteCategory(Integer id) {
		System.out.println("deleting category");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Category category = categoryService.getCategory(user.getId(), id);
		categoryService.deleteCategory(category);
		return category;
	}
}
