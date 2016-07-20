package me.tkachenko.myfirst.workersDAO.impl;

import me.tkachenko.myfirst.model.Worker;
import me.tkachenko.myfirst.workersDAO.WorkersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.util.List;

/**
 * Created by ִלטענטי on 19.07.2016.
 */
public class WorkersDAOImpl implements WorkersDAO {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;


    @Override
    public List<Worker> getAllWorkers() {


        return sessionFactory.getObject().getCurrentSession().createCriteria(Worker.class).list();
    }
}
