package me.tkachenko.myfirst;

import me.tkachenko.myfirst.config.BeanConfig;
import me.tkachenko.myfirst.config.DBReader;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ִלטענטי on 15.07.2016.
 */
@Component

public class WorkersDAO {

    private Session session;
    ApplicationContext context =
            new AnnotationConfigApplicationContext(BeanConfig.class);
    DBReader reader = context.getBean(DBReader.class);



    public List<Worker> getAllWorkers() {
        session = reader.getSessionFactory().getCurrentSession();
        return this.session.createQuery("SELECT * FROM  workers").list();
    }
}
