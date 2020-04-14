package com.bym.bankingsystem;

import com.bym.bankingsystem.seeds.ApplicationSeeds;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class BankingSystemApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext app =  SpringApplication.run(BankingSystemApplication.class, args);
        ApplicationSeeds applicationSeeds = app.getBean(ApplicationSeeds.class);
        applicationSeeds.init();
    }

}
