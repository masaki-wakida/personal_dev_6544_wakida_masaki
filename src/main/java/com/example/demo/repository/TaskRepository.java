package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
	// SELECT * FROM tasks WHERE category_id = ?
	List<Task> findByUserIdAndCategoryId(Integer userId, Integer categoryId);
	
	List<Task> findByUserIdAndTitleContaining(Integer userId, String keyword);
		
	List<Task> findByUserIdAndCategoryIdAndTitleContaining(Integer userId, Integer categoryId, String keyword);
	
	List<Task> findByUserId(Integer userId);
	
}
