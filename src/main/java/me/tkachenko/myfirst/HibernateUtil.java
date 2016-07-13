package me.tkachenko.myfirst; /**
 * Created by Sergej Tkachenko on 22.03.2016.
 */


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    static {
        Configuration cfg = new Configuration().configure();

        cfg.addAnnotatedClass(Worker.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());

        sessionFactory = cfg.buildSessionFactory(builder.build());

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void closeFactory() {
        sessionFactory.close();
    }
}
