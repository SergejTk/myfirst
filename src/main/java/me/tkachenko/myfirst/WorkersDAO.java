package me.tkachenko.myfirst;

import me.tkachenko.myfirst.config.BeanConfig;
import me.tkachenko.myfirst.config.DBReader;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by ִלטענטי on 15.07.2016.
 */
public class WorkersDAO {

    private DBReader reader;
    private Session session = reader.getSession();
    ApplicationContext context =
            new AnnotationConfigApplicationContext(BeanConfig.class);
    DBReader dbReader = context.getBean(DBReader.class);

    @Autowired
    public WorkersDAO(DBReader dbReader) {
        reader = dbReader;
    }

    public List<Worker> getAllWorkers() {
        return this.session.createQuery("SELECT * FROM  workers").list();
    }
}
