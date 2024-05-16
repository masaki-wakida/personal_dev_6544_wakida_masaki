package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	//	ユーザ登録画面の表示
	@GetMapping({"/users","/new"})
	public String create() {

		return "login";
	}

	//	ユーザー登録を実行
	@PostMapping({"/users","/add"})
	public String add(
		@RequestParam(name = "email", defaultValue = "") String email,
		@RequestParam(name = "name", defaultValue = "") String name,
		@RequestParam(name = "password", defaultValue = "") String password,
		@RequestParam(name = "passwordConfirm", defaultValue = "") String passwordConfirm,
		Model model) {

		return "login";
	}


	// ログイン画面を表示
	@GetMapping({"/login", "/logout"})
	public String index() {
//		@RequestParam(name = "error", defaultValue = "") String error,
//		Model model) {
//			// セッション情報を全てクリアする
//			session.invalidate();
//			// エラーパラメータのチェック
//			if (error.equals("notLoggedIn")) {
//				model.addAttribute("message", "ログインしてください");
//			}

			return "login";
		}

		// ログインを実行
		@PostMapping("/login")
		public String login(
				@RequestParam("name") String name,
				Model model) {
//			// 名前が空の場合にエラーとする
//			if (name == null || name.length() == 0) {
//				model.addAttribute("message", "名前を入力してください");
//				return "login";
//			}
			// 「/taskList」へのリダイレクト
			return "redirect:/taskList";

		}

		@GetMapping({"/logout"}) 
		public String logout() {

			return "login";
		}

	}
