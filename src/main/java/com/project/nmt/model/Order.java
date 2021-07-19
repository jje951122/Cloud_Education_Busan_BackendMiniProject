package com.project.nmt.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "\"order\"")
@Entity
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
