package com.sucsoft.easyudcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableAsync
@EnableSwagger2
@EnableScheduling
@SpringBootApplication(scanBasePackages ={ "com.sucsoft.easyudcore","com.sucsoft.easyudexception"})
public class EasyUdCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyUdCoreApplication.class, args);
    }

}
