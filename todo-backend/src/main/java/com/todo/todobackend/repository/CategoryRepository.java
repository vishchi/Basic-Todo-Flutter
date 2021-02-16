package com.todo.todobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.todobackend.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	

}
