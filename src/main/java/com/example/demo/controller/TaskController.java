package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Task;
import com.example.demo.model.Account;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TaskRepository;

@Controller
public class TaskController {
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	Account account;

	@Autowired
	CategoryRepository categoryRepository;


	@GetMapping({"/tasks"}) 
	public String index( 			
		@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
		@RequestParam(name = "keyword", defaultValue = "") String keyword,
		Model model) {
	
//	 categoryテーブルから全カテゴリー一覧を取得
	List<Category> categoryList = categoryRepository.findAll();
	model.addAttribute("categories", categoryList);

//	DBで数値で管理しているカテゴリー名、重要度、進捗状況をMapで文字に変換
	Map<Integer,String> categoryMap = new HashMap<>();
	categoryMap.put(1, "仕事");
	categoryMap.put(2, "個人");
	categoryMap.put(3, "その他");
	
	model.addAttribute("categoryMap",categoryMap);
	
	Map<Integer,String> importanceMap = new HashMap<>();
	importanceMap.put(0, "高");
	importanceMap.put(1, "中");
	importanceMap.put(2, "低");
	
	model.addAttribute("importanceMap",importanceMap);
	
	Map<Integer,String> progressMap = new HashMap<>();
	progressMap.put(0, "未着手");
	progressMap.put(1, "進行中");
	progressMap.put(2, "完了");
	
	model.addAttribute("progressMap",progressMap);
	
//	userIdの取得
	Integer userId = account.getId();	
	
// 	商品一覧情報の取得
	List<Task> taskList = null;
	if (categoryId != null) {
		taskList = taskRepository.findByUserIdAndCategoryId(userId, categoryId);
		

	} else if (keyword.length() > 0) {
		// 商品名による部分一致検索
//		SELECT * FROM items WHERE name LIKE 
		taskList = taskRepository.findByUserIdAndTitleContaining(userId, keyword);
		
	} else {
		// テーブルをカテゴリーIDを指定して一覧を取得
		taskList = taskRepository.findByUserId(userId);
	}
	
	model.addAttribute("keyword", keyword);
	model.addAttribute("tasks", taskList);
	
	return "taskList";
}
	
	@GetMapping({"/tasks/add"}) 
	public String create() {
		return "taskAdd";
	}
//	タスク新規登録
	@PostMapping({"/tasks/add"}) 
	public String add(
		@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
		@RequestParam(value = "title", defaultValue = "") String title,
		@RequestParam(value = "limitDate", defaultValue = "") LocalDate limitDate,
		@RequestParam(value = "importance", defaultValue = "") Integer importance,
		@RequestParam(value = "progress", defaultValue = "") Integer progress,
		@RequestParam(value = "memo", defaultValue = "") String memo,
		Model model) {
		
//	userIdの取得
	Integer userId_taskAdd = account.getId();	
	
	// エラーチェック
	List<String> taskErrorList = new ArrayList<>();
	
	if (title.length() == 0) {
		taskErrorList.add("タイトルを入力してください");
	}
	if (categoryId == null) {
		taskErrorList.add("カテゴリーを入力してください");
	}
	if (limitDate == null) {
		taskErrorList.add("期限日を入力してください");
	}
	if (progress == null) {
		taskErrorList.add("進捗状況を入力してください");
	}
	

	// エラー発生時はお問い合わせフォームに戻す
	if (taskErrorList.size() > 0) {
		model.addAttribute("errorList", taskErrorList);
		return "taskAdd";
	}
		
	// taskオブジェクトの生成
	Task task = new Task(categoryId, userId_taskAdd, title, limitDate, importance, progress, memo);
	// tasksテーブルへの反映（INSERT）
	taskRepository.save(task);
	// 「/tasks」にGETでリクエストし直す（リダイレクト）
		return "redirect:/tasks";
	}
	
	@GetMapping({"/tasks/{id}/detail"}) 
	public String detail (@PathVariable("id") Integer id, Model model) {

		// itemsテーブルをID（主キー）で検索
		Task taskList = taskRepository.findById(id).get();
		model.addAttribute("task", taskList);
		
		return "taskDetail";
	}
	
	@PostMapping({"/tasks/{id}/update"}) 
	public String update(
		@PathVariable("id") Integer id,
		@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
		@RequestParam(value = "title", defaultValue = "") String title,
		@RequestParam(value = "limitDate", defaultValue = "") LocalDate limitDate,
		@RequestParam(value = "importance", defaultValue = "") Integer importance,
		@RequestParam(value = "progress", defaultValue = "") Integer progress,
		@RequestParam(value = "memo", defaultValue = "") String memo,
		Model model) {

//		userIdの取得
		Integer userId_taskUpdate = account.getId();	
		
		// taskオブジェクトの生成
		Task task = new Task(id, categoryId, userId_taskUpdate, title, limitDate, importance, progress, memo);
		// tasksテーブルへの反映（UPDATE）
		taskRepository.save(task);
		// 「/tasks」にGETでリクエストし直す（リダイレクト）
		return "redirect:/tasks";
	}
	
	@PostMapping({"/tasks/{id}/delete"}) 
	public String delete(@PathVariable("id") Integer id, Model model) {
		
		// tasksテーブルから削除（DELETE）
		taskRepository.deleteById(id);
		// 「/tasks」にGETでリクエストし直す（リダイレクト）
		return "redirect:/tasks";

	}
	
	@GetMapping("/tasks/{sort}")
	public String sort(
			@PathVariable("sort") String sort,
			Model model) {

//		userIdの取得
		Integer userId_taskAdd = account.getId();	
		
		List<Task> Task = null;
		switch (sort) {
		case "title":
			Task = taskRepository.findByUserIdOrderByTitleAsc(userId);
			break;
		case "eltit":
			Task = taskRepository.findByUserIdOrderByTitleDesc(userId);
			break;
		case "cat":
			Task = taskRepository.findByUserIdOrderByCategoryIdAsc(userId);
			break;
		case "tac":
			Task = taskRepository.findByUserIdOrderByCategoryIdDesc(userId);
			break;
		case "imp":
			Task = taskRepository.findByUserIdOrderByImportanceAsc(userId);
			break;
		case "pmi":
			Task = taskRepository.findByUserIdOrderByImportanceDesc(userId);
			break;
		case "prog":
			Task = taskRepository.findByUserIdOrderByProgressAsc(userId);
			break;
		case "gorp":
			Task = taskRepository.findByUserIdOrderByProgressDesc(userId);
			break;
		case "lim":
			Task = taskRepository.findByUserIdOrderByLimitDateAsc(userId);
			break;
		case "mil":
			Task = taskRepository.findByUserIdOrderByLimitDateDesc(userId);
			break;
		}
		model.addAttribute("task", Task);
		model.addAttribute("cm", cm);
		model.addAttribute("im", im);
		model.addAttribute("pm", pm);
		return "taskList";
	}
}

