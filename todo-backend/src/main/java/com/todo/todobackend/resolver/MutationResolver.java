package com.todo.todobackend.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.todo.todobackend.entity.Category;
import com.todo.todobackend.entity.Todo;
import com.todo.todobackend.entity.User;
import com.todo.todobackend.entity.input.InsertCategoryInput;
import com.todo.todobackend.entity.input.InsertTodoInput;
import com.todo.todobackend.entity.input.InsertUserInput;
import com.todo.todobackend.entity.output.SignInUser;
import com.todo.todobackend.repository.CategoryRepository;
import com.todo.todobackend.repository.TodoRepository;
import com.todo.todobackend.repository.UserRepository;
import com.todo.todobackend.security.annotations.Unsecured;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class MutationResolver implements GraphQLMutationResolver {

	@Autowired
	TodoRepository todoRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	UserRepository userRepository;

	// User Mutations
	@Unsecured
	public SignInUser insertUser(InsertUserInput input) {
		System.out.println("Inserting User");
		User user = userRepository.findByEmail(input.getEmail());
		if (user == null) {
			user = new User();
			user.setEmail(input.getEmail());
			user.setPassword(input.getPassword()); // TODO hash first
			userRepository.save(user);
			String token = ""; // TODO use JWT
			SignInUser output = new SignInUser(input.getEmail(), token);
			return output;
		}
		// TODO throw "User exists" error
		return null;

	}

	// Insert Mutations
	public Todo insertTodo(InsertTodoInput input) {
		System.out.println("Inserting todo");
//		Category category = categoryRepository.findByName
		Todo todo = new Todo();
		todo.setTitle(input.getTitle());
		todo.setDescription(input.getDescription());
		todoRepository.save(todo);
		System.out.println(todo);
		return todo;
	}

	public Category insertCategory(InsertCategoryInput input) {
		System.out.println("Inserting category");
		Category category = new Category();
		category.setName(input.getName());
		categoryRepository.save(category);
		System.out.println(category);
		return category;
	}

	// Update Mutations
	public Todo updateTodo(Integer id, InsertTodoInput input) {
		System.out.println("Updating todo");
		Todo todo = new Todo();
		todo.setId(id);
		todo.setTitle(input.getTitle());
		todo.setDescription(input.getDescription());
//		todo.setCategory(input.getCategory());
//		int result = todoRepository.updateTodo(todo.getId(), todo.getTitle(), todo.getDescription(), todo.getCategory());
//		System.out.println(result);
		return todo;

	}

	public Category updateCategory(Integer id, InsertCategoryInput input) {
		System.out.println("Updating category");
		Category category = new Category();
		category.setName(input.getName());
		category.setId(id);
//		int result = categoryRepository.updateCategory(category.getId(), category.getName());
//		System.out.println(result);
		return category;
	}

	// delete Mutations
	public Todo deleteTodo(Integer id) {
		System.out.println("deleting todo");
		Todo todo = todoRepository.findById(id).orElse(null);
		System.out.println(todo);
		todoRepository.deleteById(id);
		return todo;
	}

	public Category deleteCategory(Integer id) {
		System.out.println("deleting category");
		Category category = categoryRepository.findById(id).orElse(null);
		System.out.println(category);
		categoryRepository.deleteById(id);
		return category;
	}
}
