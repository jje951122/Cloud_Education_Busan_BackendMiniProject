package com.project.nmt.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;     // 종목 이름
    private String keyword;  // 키워드
    private int quantity;    // 종목 잔여 수량

    @OneToMany(mappedBy = "stock")
    private final List<StockInfo> stockInfos = new ArrayList<>();


    @Builder
    public Stock(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
