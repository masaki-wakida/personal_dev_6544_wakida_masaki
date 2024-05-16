package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class TaskController {

	@GetMapping({"/tasks"}) 
	public String index() {
		
		return "tasks";
	}
	
	@GetMapping({"/tasks/new"}) 
	public String create() {
		
		return "tasks";
	}
	
	@GetMapping({"/tasks/new"}) 
	public String add() {
		
		return "tasks";
	}
}

