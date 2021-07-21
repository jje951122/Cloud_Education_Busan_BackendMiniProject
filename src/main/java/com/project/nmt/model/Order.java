package com.project.nmt.model;

import javax.persistence.*;


import lombok.Data;

import java.time.LocalDate;

@Table(name = "\"order\"")
@Entity
@Data
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
    private Stock stock;

}
