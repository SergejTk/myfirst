package me.tkachenko.myfirst;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by ִלטענטי on 11.07.2016.
 */
public class MyFirstProba {

    public static void main(String[] args){

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        for(int i = 1; i <= 20; i++) {
            RecordPeople recordPeople = new RecordPeople();
            recordPeople.record(i, session);
        }
        session.close();
        System.exit(0);
    }
}
