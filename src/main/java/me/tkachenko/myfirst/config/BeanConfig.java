package me.tkachenko.myfirst.config;

/**
 * Created by ִלטענטי on 14.07.2016.
 */


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    ReaderFromDB readerFromDB() {
        return new ReaderFromDB();
    }
}
