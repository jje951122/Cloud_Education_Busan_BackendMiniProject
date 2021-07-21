package com.project.nmt.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
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

    @Builder
    public OrderLog(LocalDate boughtDate, LocalDate soldDate, Integer boughtPrice, Integer soldPrice, Integer soldQuantity, User user, Stock stock) {
        this.boughtDate = boughtDate;
        this.soldDate = soldDate;
        this.boughtPrice = boughtPrice;
        this.soldPrice = soldPrice;
        this.soldQuantity = soldQuantity;
        this.user = user;
        this.stock = stock;
    }
}
