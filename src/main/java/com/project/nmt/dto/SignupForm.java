package com.project.nmt.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignupForm {
	private String userId;
	private String password;
	private String name;
	private int age;
	private String email;
	
}
