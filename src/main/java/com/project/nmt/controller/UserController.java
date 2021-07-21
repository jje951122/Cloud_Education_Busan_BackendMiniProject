package com.project.nmt.controller;

import javax.servlet.http.HttpSession;

import com.project.nmt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.nmt.dto.LogInDto;
import com.project.nmt.dto.SignupForm;
import com.project.nmt.model.User;
import com.project.nmt.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;

	@GetMapping("/signup")
	public String getSignup() {
		return "user/signup";
	}

	@PostMapping("/signup")
	public String postSignup(SignupForm signUpForm) {
		userService.createNewUser(signUpForm);

		return "redirect:/";
	}

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

	@PostMapping("/login")
	public String postLogin(LogInDto logIn, HttpSession session) {
		User user = userService.getOneByUserId(logIn.getUserId());

		session.setAttribute("id", user);

		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("id");

		return "redirect:/";
	}
}
