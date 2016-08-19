package me.tkachenko.myfirst.workersdao;

import me.tkachenko.myfirst.model.Worker;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Sergej on 19.07.2016.
 */
@Repository
public class WorkersDAOImpl implements WorkersDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Worker> getAllWorkers() {


        return sessionFactory.getCurrentSession().createCriteria(Worker.class).list();
    }

    @Override
    public List<Worker> getPartWorkers(int start, int length, String columnName, boolean isAsc) {

        if (columnName == null) columnName = "def";


        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Worker.class)
                .setFirstResult(start)
                .setMaxResults(length);

        if (isAsc) criteria = criteria.addOrder(Order.asc(columnName));
        else criteria = criteria.addOrder(Order.desc(columnName));

        return criteria.list();


    }

    @Override
    public Number getTotalRow() {

        return (Number) sessionFactory.getCurrentSession()
                .createCriteria(Worker.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();


    }


}
