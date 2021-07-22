package com.project.nmt.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class StockInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int price;           //  당일 가격
    private LocalDate infoDate;  //  주식 정보 날짜

    @ManyToOne
    private Stock stock;

}
