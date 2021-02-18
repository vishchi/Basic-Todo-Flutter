package com.todo.todobackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todobackend.entity.Category;
import com.todo.todobackend.entity.Todo;
import com.todo.todobackend.entity.User;
import com.todo.todobackend.repository.TodoRepository;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepo;
	
	public List<Todo> getTodosByUserId(Integer userId) {
		return todoRepo.findByUser_Id(userId);
	}
	
	public Todo getTodo(Integer userId, Integer id) {
		return todoRepo.findByIdAndUser_Id(id, userId);
	}
	
	public List<Todo> getTodosByCat(Integer userId, Integer catId) {
		return todoRepo.findByUser_IdAndCategory_Id(userId, catId);
	}
	
	public Todo insertTodo(String title, String desc, Category category, User user) {
		Todo todo = new Todo();
		todo.setTitle(title);
		todo.setDescription(desc);
		todo.setUser(user);
		todo.setCategory(category);
		todo = todoRepo.save(todo);
		return todo;
	}

	public Todo updateTodo(Todo todo, String title, String desc, Category cat) {
		todo.setTitle(title);
		if (desc != null) {
			todo.setDescription(desc);
		}
		if (cat != null) {
			todo.setCategory(cat);
		}
		todo = todoRepo.save(todo);
		System.out.println(todo);
		return todo;
	}
	
	public void deleteTodo(Todo todo) {
		System.out.println(todo);
		todoRepo.delete(todo);
	}
}
