package com.project.nmt.controller;

import com.project.nmt.dto.LogInDto;
import com.project.nmt.dto.SignupForm;
import com.project.nmt.model.User;
import com.project.nmt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;

	@GetMapping("/signUp")
	public String getSignUp() {
		return "user/signup";
	}

	@PostMapping("/signUp")
	public String postSignUp(SignupForm signUpForm) {
		userService.createNewUser(signUpForm);

		return "redirect:/";
	}

	@GetMapping("/logIn")
	public String login() {
		return "user/login";
	}

	@PostMapping("/logIn")
	public String postLogin(LogInDto logIn, HttpSession session) {
		User user = userService.getOneByUserId(logIn.getUserId());

		session.setAttribute("user", user);

		return "redirect:/";
	}

	@GetMapping("/logOut")
	public String logOut(HttpSession session) {
		session.removeAttribute("user");

		return "redirect:/";
	}
}
