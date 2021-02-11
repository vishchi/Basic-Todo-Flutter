package com.todo.todobackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.todo.todobackend.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
	
	List<Todo> findByCategory(String category);
	
	@Transactional
	@Modifying
	@Query("update Todo t set t.title=:title, t.description=:description, t.category=:category where t.id=:id")
	int updateTodo(@Param("id") Integer id, @Param("title") String title, @Param("description") String description, @Param("category") String category);

}
