package com.project.nmt.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String keyword;
    private String title;
    private String url;

    @Builder
    public Article(String title, String url) {
        this.title = title;
        this.url = url;
    }
}
