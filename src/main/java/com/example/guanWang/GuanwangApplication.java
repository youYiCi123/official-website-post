package com.example.guanWang;

import com.example.guanWang.api.common.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.example.guanWang")
@MapperScan("com.example.guanWang.mapper")
public class GuanwangApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuanwangApplication.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

}
