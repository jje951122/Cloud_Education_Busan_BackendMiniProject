package com.project.nmt.dto;

import com.project.nmt.model.Stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserBudgetDto {
	private String name;
	private int budget;
	private String userId;
	
	private Stock stock;
	private int quantity;
}
