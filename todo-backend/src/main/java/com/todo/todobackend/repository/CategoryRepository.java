package com.todo.todobackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.todobackend.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	Category findByNameAndUser_Id(String name, Integer userId);
	
	Category findByIdAndUser_Id(Integer id, Integer userId);
	
	List<Category> findByUser_Id(Integer userId);

}
