package com.project.nmt.controller;

import javax.servlet.http.HttpSession;

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
	private final UserRepository userRepository;
	
	
	@GetMapping("/signup")
	public String getSignup() {
		return "user/signup";
	}
	
	@PostMapping("/signup")
	public String postSignup(SignupForm signUpForm) {
		User user = User.builder()
		.userId(signUpForm.getUserId())
		.password(signUpForm.getPassword())
		.name(signUpForm.getName())
		.age(signUpForm.getAge())
		.email(signUpForm.getEmail())
		.build();	
		
		userRepository.save(user);
		
		
		
		
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@PostMapping("/login")
	public String postLogin(LogInDto logIn, HttpSession session) {
		User user = userRepository.findByUserId(logIn.getUserId());
		
		
		session.setAttribute("id", user);

		
		
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("id");
		
		return "redirect:/";
	}
	
	
	
	
	
	
}
