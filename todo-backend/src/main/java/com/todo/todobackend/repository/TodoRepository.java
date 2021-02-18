package com.todo.todobackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.todobackend.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
	
	List<Todo> findByUser_IdAndCategory_Id(Integer userId, Integer catId);
	
	List<Todo> findByUser_Id(Integer id);
	
	Todo findByIdAndUser_Id(Integer id, Integer userId);
	
}
