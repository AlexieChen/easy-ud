package com.sucsoft.easyudsql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.sucsoft.easyudsql.repository")
public class EasyUdSqlApplication {
	public static void main(String[] args) {
		SpringApplication.run(EasyUdSqlApplication.class, args);
	}

}
