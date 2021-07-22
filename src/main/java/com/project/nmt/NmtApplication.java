package com.project.nmt;

import com.project.nmt.dataSetting.dataSetting;
import com.project.nmt.model.OrderLog;
import com.project.nmt.model.Stock;
import com.project.nmt.model.User;
import com.project.nmt.repository.OrderLogRepository;
import com.project.nmt.repository.StockRepository;
import com.project.nmt.repository.UserRepository;
import com.project.nmt.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.LocalDate;

@SpringBootApplication
public class NmtApplication implements CommandLineRunner {

    @Autowired
    dataSetting dataSet;
    @Autowired
    private ArticleService articleService;

    public static void main(String[] args) {
        SpringApplication.run(NmtApplication.class, args);
    }

    @Override
    public void run(String... args) {
        articleService.scrapAll();
    }
}
