package com.project.nmt.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Table(name = "\"user\"")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;          // 아이디
    private String password;        // 비번
    private String name;            // 이름
    private int age;                // 나이
    private String email;           // 메일 주소
    private int budget;             // 자산

    @OneToMany(mappedBy = "user")
    private final List<Order> order = new ArrayList<>();


    @Builder
    public User(String userId, String password, String name, int age, String email, int budget) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.age = age;
        this.email = email;
        this.budget = budget;
    }
}
