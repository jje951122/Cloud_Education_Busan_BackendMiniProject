package com.project.nmt.model;

import javax.persistence.*;

import com.project.nmt.dto.UserBudgetDto;
import com.project.nmt.dto.UserOrderDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Table(name = "\"order\"")
@Entity
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int price;
    private LocalDate boughtDate;
    private int quantity;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "name")
    private Stock stock;
    
    public UserOrderDto convertOrderDto() {
    	return new UserOrderDto(this.quantity, this.stock);
    }
    
    @Builder
	public Order(int price, LocalDate boughtDate, int quantity, User user, Stock stock) {
		super();
		this.price = price;
		this.boughtDate = boughtDate;
		this.quantity = quantity;
		this.user = user;
		this.stock = stock;
	}
}