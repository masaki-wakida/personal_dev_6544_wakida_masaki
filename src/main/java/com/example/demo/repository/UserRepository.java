package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

	public interface UserRepository extends JpaRepository<User, Integer> {

		// SELECT * FROM customer WHERE email = ? AND password = ?
		List<User> findByEmailAndPassword(String email, String password);

		// SELECT * FROM customer WHERE email = ?
		List<User> findByEmail(String email);
		
}
