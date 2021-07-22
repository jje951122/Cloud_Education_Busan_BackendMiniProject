package com.project.nmt;

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

import java.time.LocalDate;

@SpringBootApplication
public class NmtApplication implements CommandLineRunner {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private OrderLogRepository orderLogRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleService articleService;

    public static void main(String[] args) {
        SpringApplication.run(NmtApplication.class, args);
    }

    @Override
    public void run(String... args) {
//
//        User user = User.builder()
//                .name("park")
//                .userId("asdf")
//                .password("123")
//                .email("asdf@naver.com")
//                .age(17)
//                .build();
//
//        userRepository.save(user);
//
//        Stock potato = Stock.builder()
//                .name("potato")
//                .keyword("감자")
//                .quantity(100)
//                .build();
//        Stock sweetPotato = Stock.builder()
//                .name("sweet potato")
//                .keyword("고구마")
//                .quantity(300)
//                .build();
//
//        Stock corn = Stock.builder()
//                .name("corn")
//                .keyword("옥수수")
//                .quantity(450)
//                .build();
//
//        Stock onion = Stock.builder()
//                .name("onion")
//                .keyword("양파")
//                .quantity(150)
//                .build();
//
//        Stock leek = Stock.builder()
//                .name("leek")
//                .keyword("대파")
//                .quantity(400)
//                .build();
//
//        Stock garlic = Stock.builder()
//                .name("garlic")
//                .keyword("마늘")
//                .quantity(900)
//                .build();
//
//        stockRepository.save(potato);
//        stockRepository.save(sweetPotato);
//        stockRepository.save(corn);
//        stockRepository.save(garlic);
//        stockRepository.save(onion);
//        stockRepository.save(leek);
//
//
//        OrderLog ol1 = OrderLog.builder()
//                .boughtDate(LocalDate.of(2021, 7, 1))
//                .boughtPrice(5000)
//                .soldDate(LocalDate.of(2021, 7, 3))
//                .soldPrice(6000)
//                .soldQuantity(10)
//                .stock(potato)
//                .user(user)
//                .build();
//        OrderLog ol2 = OrderLog.builder()
//                .boughtDate(LocalDate.of(2021, 7, 3))
//                .boughtPrice(4200)
//                .stock(potato)
//                .user(user)
//                .build();
//        OrderLog ol3 = OrderLog.builder()
//                .boughtDate(LocalDate.of(2021, 7, 5))
//                .boughtPrice(3500)
//                .soldDate(LocalDate.of(2021, 7, 6))
//                .soldPrice(3000)
//                .soldQuantity(15)
//                .stock(sweetPotato)
//                .user(user)
//                .build();
//        OrderLog ol4 = OrderLog.builder()
//                .boughtDate(LocalDate.of(2021, 7, 7))
//                .boughtPrice(3000)
//                .stock(sweetPotato)
//                .user(user)
//                .build();
//
//
//        orderLogRepository.save(ol1);
//        orderLogRepository.save(ol2);
//        orderLogRepository.save(ol3);
//        orderLogRepository.save(ol4);

//        articleService.scrapAll();
    }
}
