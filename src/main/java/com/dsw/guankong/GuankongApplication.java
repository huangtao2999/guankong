package com.dsw.guankong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@MapperScan("com.dsw.guankong.dal")
@EnableTransactionManagement
//@EnableScheduling
public class GuankongApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuankongApplication.class, args);
    }
}
