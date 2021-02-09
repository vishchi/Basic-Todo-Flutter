package com.todo.todobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.todobackend.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer>{

}
