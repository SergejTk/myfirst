package me.tkachenko.myfirst.workersdao;

import me.tkachenko.myfirst.model.Worker;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by ִלטענטי on 19.07.2016.
 */
@Repository
public class WorkersDAOImpl implements WorkersDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Worker> getAllWorkers() {


        return sessionFactory.getCurrentSession().createCriteria(Worker.class).list();
    }
}
