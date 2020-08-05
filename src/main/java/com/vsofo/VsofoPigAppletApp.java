package com.vsofo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(value = {"com.vsofo.applet.pigfarmstat.mapper","com.vsofo.authentication.mapper"})
@EnableScheduling
//@EnableCaching
public class VsofoPigAppletApp {

    public static void main(String[] args) {
        SpringApplication.run(VsofoPigAppletApp.class, args);
    }

}
