package me.tkachenko.myfirst;

import org.hibernate.SessionFactory;

/**
 * Created by ������� on 15.07.2016.
 */
public interface DBReader {
    SessionFactory getSessionFactory();

}