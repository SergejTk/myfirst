package me.tkachenko.myfirst.workersDAO.impl;

import me.tkachenko.myfirst.model.Worker;
import me.tkachenko.myfirst.workersDAO.WorkersDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ִלטענטי on 19.07.2016.
 */
@Component
public class WorkersDAOImpl implements WorkersDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Worker> getAllWorkers() {


        return sessionFactory.openSession().createCriteria(Worker.class).list();
    }
}
