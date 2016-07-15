package me.tkachenko.myfirst.config;

/**
 * Created by ִלטענטי on 14.07.2016.
 */


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    DBReader dbReader() {

        return new DBReader() {
            public Session getSession() {
                SessionFactory sessionFactory = null;
                return sessionFactory.getCurrentSession();

            }

        };
    }
}
