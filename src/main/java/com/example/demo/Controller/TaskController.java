package com.example.demo.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Task;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TaskRepository;

@Controller
public class TaskController {
	
	@Autowired
	TaskRepository taskRepository;

	@Autowired
	CategoryRepository categoryRepository;


	@GetMapping({"/tasks"}) 
	public String index( 			
		@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
		Model model) {

//	 categoryテーブルから全カテゴリー一覧を取得
	List<Category> categoryList = categoryRepository.findAll();
	model.addAttribute("categories", categoryList);

// 	商品一覧情報の取得
	List<Task> taskList = null;
	if (categoryId == null) {
		taskList = taskRepository.findAll();
	} else {
		// テーブルをカテゴリーIDを指定して一覧を取得
		taskList = taskRepository.findByCategoryId(categoryId);
	}
	model.addAttribute("tasks", taskList);
	
	return "taskList";
}
	
	@GetMapping({"/tasks/add"}) 
	public String create() {
		return "taskAdd";
	}
	
	@PostMapping({"/tasks/add"}) 
	public String add(
		@RequestParam(value = "title", defaultValue = "") String title,
		@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
		@RequestParam(value = "limitDacategoryIdte", defaultValue = "") Date limitDate,
		@RequestParam(value = "importance", defaultValue = "") Integer importance,
		@RequestParam(value = "progress", defaultValue = "") Integer progress,
		@RequestParam(value = "memo", defaultValue = "") String memo,
		Model model) {
		
	// Itemオブジェクトの生成
	Task task = new Task(title, limitDate, importance, progress, memo);
	// itemsテーブルへの反映（INSERT）
	taskRepository.save(task);
	// 「/items」にGETでリクエストし直す（リダイレクト）
		
		return "redirect:/tasks";
	}
	
	@GetMapping({"/tasks/{id}/detail"}) 
	public String detail() {
		
		return "tasks";
	}
	
	@PostMapping({"/tasks/{id}/update"}) 
	public String update() {
		
		return "tasks";
	}
	
	@PostMapping({"/tasks/{id}/delete"}) 
	public String delete() {
		
		return "tasks";
	}
}

