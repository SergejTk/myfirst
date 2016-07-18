package me.tkachenko.myfirst.config;

/**
 * Created by ִלטענטי on 14.07.2016.
 */


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.util.Properties;

@Configuration
//@PropertySources( {"classpath:DBProperties.properties" })
public class BeanConfig {
    @Autowired
    private Environment env;

    @Bean
    DBReader dbReader() {

        return new DBReader() {
            @Override
            public SessionFactory getSessionFactory() {
                LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
                SessionFactory sessionFactory;
                //

                localSessionFactoryBean.setHibernateProperties(hibernateProperties());
                sessionFactory = localSessionFactoryBean.getObject();
                return sessionFactory;
            }
        };
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", "update");
                setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
                setProperty("hibernate.globally_quoted_identifiers", "true");
            }
        };
    }
}
