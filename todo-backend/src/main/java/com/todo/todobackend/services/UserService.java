package com.todo.todobackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todobackend.entity.User;
import com.todo.todobackend.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	// Creating a user
	public User insertUser(String email, String password) {
		User user = userRepo.findByEmail(email);
		if (user == null) {
			user = new User();
			user.setEmail(email);
			user.setPassword(password); 
			userRepo.save(user);
			return user;
		}
		return null;
	}
	
	// Get user by name and password
	public User getUser(String email) {
		return userRepo.findByEmail(email);
	}

}
