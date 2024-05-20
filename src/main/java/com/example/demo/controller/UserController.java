package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	Account account;
	
	@Autowired
	UserRepository userRepository;

	
	//	ユーザ登録画面の表示
	@GetMapping({"/users/add"})
	public String cueate() {

		return "userAdd";
	}

	//	ユーザー登録を実行
	@PostMapping({"/users/add"})
	public String add(
		@RequestParam(name = "email", defaultValue = "") String email,
		@RequestParam(name = "name", defaultValue = "") String name,
		@RequestParam(name = "password", defaultValue = "") String password,
		Model model) {
		
		// エラーチェック
		List<String> errorList = new ArrayList<>();
		if (email.length() == 0) {
			errorList.add("メールアドレスは必須です");
		}
		if (name.length() == 0) {
			errorList.add("ニックネームは必須です");
		}
		if (password.length() == 0) {
			errorList.add("パスワードは必須です");
		}
		// メールアドレス存在チェック
		List<User> userrList = userRepository.findByEmail(email);
		if (userrList != null && userrList.size() > 0) {
			// 登録済みのメールアドレスが存在した場合
			errorList.add("登録済みのメールアドレスです");
		}
		if (password.length() == 0) {
			errorList.add("パスワードは必須です");
		}

		// エラー発生時はお問い合わせフォームに戻す
		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("email", email);
			model.addAttribute("password", password);
			return "userAdd";
		}

		User user = new User(email, name, password);
		// userテーブルへの反映（INSERT）
		userRepository.save(user);
		
		return "redirect:/login";

	}

	// ログイン画面を表示
	@GetMapping({ "/", "/login", "/logout" })
	public String index(
			@RequestParam(name = "error", defaultValue = "") String error,
			Model model) {
		// セッション情報を全てクリアする
		session.invalidate();
		// エラーパラメータのチェック
		if (error.equals("notLoggedIn")) {
			model.addAttribute("message", "ログインしてください");
		}

		return "login";
	}

		// ログインを実行
		@PostMapping("/login")
		public String login(
				@RequestParam("email") String email,
				@RequestParam("password") String password,
				Model model) {
			// 名前が空の場合にエラーとする
			if (email.length() == 0 || password.length() == 0) {
				model.addAttribute("message", "入力してください");
				return "login";
			}

			List<User> userList = userRepository.findByEmailAndPassword(email, password);
			if (userList == null || userList.size() == 0) {
				// 存在しなかった場合
				model.addAttribute("message", "メールアドレスとパスワードが一致しませんでした");
				return "login";
			}
			User user = userList.get(0);

			// セッション管理されたアカウント情報にIDと名前をセット
			account.setId(user.getId());
			account.setName(user.getName());

			// 「/tasks」へのリダイレクト
			return "redirect:/tasks";
		
		}

	}
