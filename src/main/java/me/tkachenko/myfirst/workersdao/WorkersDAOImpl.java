package me.tkachenko.myfirst.workersdao;

import me.tkachenko.myfirst.model.Worker;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by Sergej on 19.07.2016.
 */
@Repository
public class WorkersDAOImpl implements WorkersDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Worker> getAllWorkers() {
        //sessionFactory.getCurrentSession().createCriteria(Worker.class).setFirstResult(2);

        return sessionFactory.getCurrentSession().createCriteria(Worker.class).list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Worker> getPartWorkers(int start, int length) {
        //List rowCount = sessionFactory.getCurrentSession().createCriteria(Worker.class).setProjection(Projections.rowCount()).list();


        return sessionFactory.getCurrentSession().createCriteria(Worker.class).addOrder(Order.asc("firstname")).setFirstResult(start).setMaxResults(length).list();
    }

    @Override
    @Transactional(readOnly = true)
    public Number getTotalRow() {

        return (Number) sessionFactory.getCurrentSession().createCriteria(Worker.class).setProjection(Projections.rowCount()).uniqueResult();


    }


}
