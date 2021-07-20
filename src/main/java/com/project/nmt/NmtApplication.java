package com.project.nmt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NmtApplication implements CommandLineRunner {

//    @Autowired
//    private StockRepository stockRepository;

    public static void main(String[] args) {
        SpringApplication.run(NmtApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // 서버 띄우자마자 실행되는 코드입니다.
        // 현재 우리 db는 인메모리 db인 h2이기 때문에, 내용이 계속 날라가게 되어있습니다.
        // 때문에 여따가 데이터를 넣어주는 코드를 박아두고 개발하시면, 서버를 다시 띄워도 얘가 넣어줍니다.
        // application.properties를 수정하셔서 서버를 꺼도 날라가지 않는 db를 사용하셔도 됩니다.

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
//        stockRepository.deleteAll();
//
//        stockRepository.save(potato);
//        stockRepository.save(sweetPotato);
//        stockRepository.save(corn);
//        stockRepository.save(garlic);
//        stockRepository.save(onion);
//        stockRepository.save(leek);
//
    }
}
