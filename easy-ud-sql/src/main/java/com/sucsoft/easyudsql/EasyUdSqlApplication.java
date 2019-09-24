package com.sucsoft.easyudsql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sucsoft.easyudsql.repository")
public class EasyUdSqlApplication {
	public static void main(String[] args) {
		SpringApplication.run(EasyUdSqlApplication.class, args);
	}

}
