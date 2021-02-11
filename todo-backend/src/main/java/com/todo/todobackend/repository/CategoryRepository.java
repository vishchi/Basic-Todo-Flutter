package com.todo.todobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.todo.todobackend.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	@Transactional
	@Modifying
	@Query("update Category c set c.name=:name where c.id=:id")
	int updateCategory(@Param("id") Integer id, @Param("name") String name);

}
