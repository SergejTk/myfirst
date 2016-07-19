package me.tkachenko.myfirst.dbreader;

/**
 * Created by ִלטענטי on 14.07.2016.
 */


import me.tkachenko.myfirst.DBReader;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.util.Properties;


@PropertySource({"classpath:DBProperties.properties"})
public class DBReaderImpl implements DBReader {
    @Autowired
    private Environment env;


    @Override
    public SessionFactory getSessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        SessionFactory sessionFactory;

        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
        sessionFactory = localSessionFactoryBean.getObject();
        return sessionFactory;
    }


    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
                setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
                setProperty("hibernate.globally_quoted_identifiers", "true");
            }
        };
    }
}
