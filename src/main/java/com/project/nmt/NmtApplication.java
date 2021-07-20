package com.project.nmt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NmtApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NmtApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 서버 띄우자마자 실행되는 코드입니다.
        // 현재 우리 db는 인메모리 db인 h2이기 때문에, 내용이 계속 날라가게 되어있습니다.
        // 때문에 여따가 데이터를 넣어주는 코드를 박아두고 개발하시면, 서버를 다시 띄워도 얘가 넣어줍니다.
        // application.properties를 수정하셔서 서버를 꺼도 날라가지 않는 db를 사용하셔도 됩니다.
    }
}
