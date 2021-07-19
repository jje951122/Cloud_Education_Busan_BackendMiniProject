package com.project.nmt.model;

import javax.persistence.*;

import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class OrderLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate boughtDate;
    private LocalDate soldDate;
    private int boughtPrice;
    private int soldPrice;
    private int soldQuantity;

    @ManyToOne
    private User user;

    @ManyToOne
    private Stock stock;
}
