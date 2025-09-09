package com.example.todoproject.todo.repository;

import com.example.todoproject.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    
    List<Todo> findByUserId(Long userId);
    
    List<Todo> findByUserIdAndIsDone(Long userId, Boolean isDone);
}