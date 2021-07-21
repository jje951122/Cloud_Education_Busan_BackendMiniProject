package com.project.nmt.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
public class Stock {

    @Id
    private Long id;

    private String name;   // 종목 이름
    private String keyword;
    private int quantity;  // 종목 잔여 수량

    @Builder
    public Stock(String name, String keyword, int quantity) {
        this.name = name;
        this.keyword = keyword;
        this.quantity = quantity;
    }
}
