package com.todo.todobackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todobackend.entity.Category;
import com.todo.todobackend.entity.User;
import com.todo.todobackend.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository catRepo;
	
	public Category getCategoryByName(String name, Integer userId) {
		return catRepo.findByNameAndUser_Id(name, userId);
	}
	
	public Category getCategory(Integer userId, Integer id) {
		return catRepo.findByIdAndUser_Id(id, userId);
	}
	
	public List<Category> getCategories(Integer userId) {
		return catRepo.findByUser_Id(userId);
	}
	
	public Category insertCategory(String name, User user) {
		Category category = new Category();
		category.setName(name);
		category.setUser(user);
		category = catRepo.save(category);
		System.out.println(category);
		return category;
	}
	
	public Category updateCategory(Category category, String name) {
		category.setName(name);
		category = catRepo.save(category);
		System.out.println(category);
		return category;
	}
	
	public void deleteCategory(Category category) {
		System.out.println(category);
		catRepo.delete(category);
	}
}
