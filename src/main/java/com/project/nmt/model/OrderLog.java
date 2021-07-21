package com.project.nmt.model;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
public class OrderLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate boughtDate;
    private LocalDate soldDate;
    private Integer boughtPrice;
    private Integer soldPrice;
    private Integer soldQuantity;

    @ManyToOne
    private User user;

    @ManyToOne
    private Stock stock;
}
