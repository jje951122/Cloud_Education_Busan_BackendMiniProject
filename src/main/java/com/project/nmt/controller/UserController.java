package com.project.nmt.controller;

import com.project.nmt.dto.LogInDto;
import com.project.nmt.dto.SignupForm;
import com.project.nmt.model.User;
import com.project.nmt.service.UserService;
import com.project.nmt.validator.UserSignupValidator;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;
	private final UserSignupValidator userSignupValidator;

	@GetMapping("/signUp")
	public String getSignUp(Model model) {
		model.addAttribute("signupForm", new SignupForm());

		return "user/signup";
	}

	@PostMapping("/signUp")
	public String postSignUp(@Valid SignupForm signUpForm, Errors errors, Model model) {
		userSignupValidator.validate(signUpForm, errors);

		if (errors.hasErrors()) {
			model.addAttribute("signupForm", signUpForm);

			return "/user/signup";
		}

		userService.createNewUser(signUpForm);

		return "redirect:/";
	}

	@GetMapping("/logIn")
	public String login() {
		return "user/login";
	}

	@PostMapping("/logIn")
	public String postLogin(LogInDto logIn, HttpSession session) {
		if(!userService.checkLogIn(logIn)) {
			return "error";
		}

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
