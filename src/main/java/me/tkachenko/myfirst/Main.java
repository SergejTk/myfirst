package me.tkachenko.myfirst;

import me.tkachenko.myfirst.config.BeanConfig;
import me.tkachenko.myfirst.model.Worker;
import me.tkachenko.myfirst.workersDAO.WorkersDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by ִלטענטי on 19.07.2016.
 */
public class Main {

    public static void main(String[] args) {
        List<Worker> list;
        ApplicationContext context =
                new AnnotationConfigApplicationContext(BeanConfig.class);
        WorkersDAO test = (WorkersDAO) context.getBean("getListAllWorkers");
        list = test.getAllWorkers();
        for (Worker worker : list)
            System.out.println(worker);
    }
}
