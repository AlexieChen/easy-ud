package com.sucsoft.easyudcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(scanBasePackages = "com.sucsoft.easyudcore")
public class EasyUdCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyUdCoreApplication.class, args);
    }

}
