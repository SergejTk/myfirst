package me.tkachenko.myfirst.config;


import me.tkachenko.myfirst.WorkersDAO;
import me.tkachenko.myfirst.WorkersDAOImpl;
import me.tkachenko.myfirst.workersgenerator.Worker;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.util.Properties;

/**
 * Created by ִלטענטי on 19.07.2016.
 */
@Configuration
@PropertySource({"classpath:DBProperties.properties"})
public class BeanConfig {

    @Autowired
    private Environment env;

    @Bean(name = "createSF")
    public SessionFactory getSessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        SessionFactory sessionFactory;

        org.hibernate.cfg.Configuration cnf = new org.hibernate.cfg.Configuration().configure();
        cnf.addAnnotatedClass(Worker.class);

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


    @Bean(name = "getListAllWorkers")
    public WorkersDAO workersDAO() {

        return new WorkersDAOImpl(getSessionFactory());
    }

}
