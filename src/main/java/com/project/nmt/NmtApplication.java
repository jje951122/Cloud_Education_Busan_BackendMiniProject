package com.project.nmt;

import com.project.nmt.dataSetting.dataSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NmtApplication implements CommandLineRunner {

    @Autowired
    dataSetting dataSet;

    public static void main(String[] args) {
        SpringApplication.run(NmtApplication.class, args);
    }

    @Override
    public void run(String... args) {
        dataSet.getData();
    }
}
