package com.project.nmt.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class StockInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int price;           //  당일 가격
    private LocalDate infoDate;  //  주식 정보 날짜

    @ManyToOne
    private Stock stock;
    

}
