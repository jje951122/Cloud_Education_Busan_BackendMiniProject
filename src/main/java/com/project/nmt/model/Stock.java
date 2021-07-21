package com.project.nmt.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Stock {

    @Id
    private Long id;


    private String name;     // 종목 이름
    private String keyword;  // 검색 키워드
    private int quantity;    // 종목 잔여 수량

    @OneToMany(mappedBy = "stock")
    private final List<StockInfo> stockInfos = new ArrayList<>();


    @Builder
    public Stock(String name, String keyword, int quantity) {
        this.name = name;
        this.keyword = keyword;
        this.quantity = quantity;
    }
}
