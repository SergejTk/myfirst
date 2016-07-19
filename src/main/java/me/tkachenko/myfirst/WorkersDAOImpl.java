package me.tkachenko.myfirst;

import me.tkachenko.myfirst.workersgenerator.Worker;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by ִלטענטי on 19.07.2016.
 */
public class WorkersDAOImpl implements WorkersDAO {
    private SessionFactory sessionFactory;

    public WorkersDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Worker> getAllWorkers() {


        return this.sessionFactory.getCurrentSession().createQuery("SELECT *  FROM workers").list();
    }
}
