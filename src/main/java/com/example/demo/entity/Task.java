package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks") // 対応するテーブル名
public class Task {

// フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // タスクID

	@Column(name = "category_id")
	private Integer categoryId; // カテゴリーID

	private String title; // タイトル

	@Column(name = "limit_date")
	private LocalDate limitDate; // 期限
	
	private Integer progress; // 進捗状況
	
	private Integer importance; // 重要度
	
	private String memo;

	//	コンストラクタ
	public Task(Integer id, String title, Integer categoryId, LocalDate limitDate, Integer progress, Integer importance, 
			String memo) {
		this.id = id;
		this.title = title;
		this.categoryId = categoryId;
		this.limitDate = limitDate;
		this.progress = progress;
		this.importance = importance;
		this.memo = memo;
}
	
	public Task(String title, Integer categoryId, LocalDate limitDate, Integer progress, Integer importance, 
			String memo) {
		this.title = title;
		this.categoryId = categoryId;
		this.limitDate = limitDate;
		this.progress = progress;
		this.importance = importance;
		this.memo = memo;
}
	
	public Task() {
	}

	//	ゲッターとセッター
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(LocalDate limitDate) {
		this.limitDate = limitDate;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public Integer getImportance() {
		return importance;
	}

	public void setImportance(Integer importance) {
		this.importance = importance;
	}
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}	

	
}
