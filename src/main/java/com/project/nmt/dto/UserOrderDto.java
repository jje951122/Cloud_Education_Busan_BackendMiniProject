package com.project.nmt.dto;


import com.project.nmt.model.Stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserOrderDto {
	private int quantity;
	private Stock stock;
}
