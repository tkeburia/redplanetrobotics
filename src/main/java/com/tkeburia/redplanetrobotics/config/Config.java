package com.tkeburia.redplanetrobotics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

import static java.lang.System.in;

@Configuration
public class Config {

    @Bean
    public Scanner scanner(){
        return new Scanner(in);
    }


}
