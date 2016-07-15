package me.tkachenko.myfirst.config;

import me.tkachenko.myfirst.Worker;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by ִלטענטי on 15.07.2016.
 */
public class ReaderFromDB {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Worker> getAllWorkers() {
        return this.sessionFactory.getCurrentSession().createQuery("SELECT * FROM  workers").list();
    }
}
