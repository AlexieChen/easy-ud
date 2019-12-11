package com.sucsoft.easyudsql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.sucsoft.easyudsql.repository")
public class EasyUdSqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyUdSqlApplication.class, args);
    }

}
