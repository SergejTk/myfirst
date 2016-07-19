package me.tkachenko.myfirst.config;

import me.tkachenko.myfirst.DBReader;
import me.tkachenko.myfirst.WorkersDAO;
import me.tkachenko.myfirst.dbreader.DBReaderImpl;
import me.tkachenko.myfirst.workersgenerator.Worker;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by ִלטענטי on 19.07.2016.
 */
@Configuration
public class BeanConfig {
    @Autowired
    DBReader reader;

    @Bean(name = "createSF")
    public DBReader dbReader() {
        return new DBReaderImpl();
    }

    @Bean(name = "getListAllWorkers")
    public WorkersDAO workersDAO() {

        return new WorkersDAO() {
            public List<Worker> getAllWorkers() {
                Session session;
                session = reader.getSessionFactory().getCurrentSession();
                return session.createQuery("SELECT * FROM  workers").list();
            }
        };
    }

}
